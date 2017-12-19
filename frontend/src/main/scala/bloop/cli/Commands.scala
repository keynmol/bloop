package bloop.cli

import caseapp.{ExtraName, HelpMessage, Recurse}

object Commands {
  sealed trait Command {
    def cliOptions: CliOptions
  }

  sealed trait CoreCommand extends Command {
    def project: String
    def scalacstyle: Boolean
  }

  case class Help(
      @Recurse cliOptions: CliOptions = CliOptions.default
  ) extends Command

  case class About(
      @Recurse cliOptions: CliOptions = CliOptions.default
  ) extends Command

  case class Compile(
      @ExtraName("p")
      @HelpMessage("The project to compile.")
      project: String,
      @HelpMessage("Compile the project incrementally. By default, true.")
      incremental: Boolean = true,
      @HelpMessage("Disable improved error message format. By default, false.")
      scalacstyle: Boolean = false,
      @ExtraName("w")
      @HelpMessage("Run the command when projects' source files change. By default, false.")
      watch: Boolean = false,
      @Recurse cliOptions: CliOptions = CliOptions.default,
  ) extends CoreCommand

  case class Projects(
      @ExtraName("dot")
      @HelpMessage("Print out a dot graph you can pipe into `dot`. By default, false.")
      dotGraph: Boolean = false,
      @Recurse cliOptions: CliOptions = CliOptions.default
  ) extends Command

  case class Test(
      @ExtraName("p")
      @HelpMessage("The project to test.")
      project: String,
      @ExtraName("all")
      @HelpMessage("Run the tests in dependencies. Defaults to true.")
      aggregate: Boolean = false,
      @HelpMessage("Disable improved error message format. By default, false.")
      scalacstyle: Boolean = false,
      @ExtraName("w")
      @HelpMessage("Run the command when projects' source files change. By default, false.")
      watch: Boolean = false,
      @Recurse cliOptions: CliOptions = CliOptions.default
  ) extends CoreCommand

  case class Clean(
      @ExtraName("p")
      @HelpMessage("The projects to clean.")
      projects: List[String],
      @Recurse cliOptions: CliOptions = CliOptions.default,
  ) extends Command

  case class Console(
      @ExtraName("p")
      @HelpMessage("The project for which to start the console.")
      project: String,
      @HelpMessage("Disable improved error message format. By default, false.")
      scalacstyle: Boolean = false,
      @HelpMessage("Start up the console compiling only the target project's dependencies.")
      excludeRoot: Boolean = false,
      @Recurse cliOptions: CliOptions = CliOptions.default
  ) extends CoreCommand
}
