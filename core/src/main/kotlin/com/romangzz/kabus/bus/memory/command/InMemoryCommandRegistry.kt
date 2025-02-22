package com.romangzz.kabus.bus.memory.command

import com.romangzz.kabus.command.Command
import com.romangzz.kabus.command.CommandHandler
import com.romangzz.kabus.command.CommandRegistry
import com.romangzz.kabus.event.Event
import io.github.classgraph.ClassGraph
import java.lang.reflect.ParameterizedType

/** In-memory implementation of the CommandRegistry interface. */
class InMemoryCommandRegistry : CommandRegistry {

  private val handlers = mutableMapOf<Class<out Command<*>>, CommandHandler<*, *>>()

  /**
   * Initializes the command registry by scanning the specified packages for command handlers.
   *
   * @param packageNames The packages to scan for command handlers.
   * @param findInstance A function to find an instance of a command handler.
   */
  @Suppress("UNCHECKED_CAST")
  fun initialize(
      vararg packageNames: String,
      findInstance:
          (command: Class<Command<*>>, handler: Class<CommandHandler<*, *>>) -> CommandHandler<
                  *, *>?
  ) {
    ClassGraph().enableAllInfo().acceptPackages(*packageNames).scan().use { scanResult ->
      val handlerClasses = scanResult.getClassesImplementing(CommandHandler::class.java.name)

      handlerClasses.forEach { handlerClassInfo ->
        val handlerClass = handlerClassInfo.loadClass() as Class<CommandHandler<*, *>>
        val genericInterface = handlerClass.genericInterfaces.firstOrNull() as? ParameterizedType
        if (genericInterface != null) {
          val commandClass = genericInterface.actualTypeArguments[1] as Class<Command<*>>
          val handler = findInstance(commandClass, handlerClass)
          if (handler != null) {
            handlers[commandClass] = handler
          }
        } else {
          error("Failed to extract command type for handler: ${handlerClass.name}")
        }
      }
    }
  }

  /**
   * Registers a command handler for a specific command.
   *
   * @param command The class of the command.
   * @param handler The command handler to register.
   * @param <E> The type of the event produced by the command.
   * @param <C> The type of the command.
   */
  override fun <E : Event, C : Command<E>> register(
      command: Class<C>,
      handler: CommandHandler<C, E>
  ) {
    require(!handlers.containsKey(command)) { "Handler already registered" }
    handlers[command] = handler
  }

  /**
   * Retrieves the command handler for a specific command.
   *
   * @param command The command instance.
   * @param <E> The type of the event produced by the command.
   * @param <C> The type of the command.
   * @return The command handler for the specified command.
   * @throws IllegalArgumentException if no handler is found for the command.
   */
  @Suppress("UNCHECKED_CAST")
  override fun <E : Event, C : Command<E>> get(command: C): CommandHandler<C, E> {
    val handler = handlers[command::class.java]
    require(handler != null) { "Handler not found" }
    return handler as CommandHandler<C, E>
  }
}
