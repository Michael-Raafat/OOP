package parser;

import java.util.ArrayList;
import java.util.List;


import exceptions.UnknownColumnDatatypeException;
import exceptions.WrongSyntaxException;
import queries.QueryAction;
import queryBuilders.QueryBuildUnit;
import syntaxManagement.AlterAddCommandPatternManager;
import syntaxManagement.AlterDropCommandPatternManager;
import syntaxManagement.CommandPatternManager;
import syntaxManagement.CreateDatabaseCommandPatternManager;
import syntaxManagement.CreateTableCommandPatternManager;
import syntaxManagement.DeleteCommandPatternManager;
import syntaxManagement.DropDatabaseCommandPatternManager;
import syntaxManagement.DropTableCommandPatternManager;
import syntaxManagement.InsertCommandPatternManager;
import syntaxManagement.SelectCommandPatternManager;
import syntaxManagement.UnionCommandPatternManager;
import syntaxManagement.UpdateCommandPatternManager;
import syntaxManagement.UseCommandPatternManager;
/**
 * IPatternManager implementation.
 * @author Amr
 *
 */
public class PatternManager implements IPatternManager {
	/**
	 * List of the commandPatternManagers which contain
	 * our syntax manager classes.
	 */
	private List<CommandPatternManager> commands;
	/**
	 * Constructor that adds all our syntax manager classes.
	 */
	public PatternManager() {
		commands = new ArrayList<CommandPatternManager>();
		commands.add(new CreateDatabaseCommandPatternManager());
		commands.add(new CreateTableCommandPatternManager());
		commands.add(new DeleteCommandPatternManager());
		commands.add(new DropDatabaseCommandPatternManager());
		commands.add(new DropTableCommandPatternManager());
		commands.add(new InsertCommandPatternManager());
		commands.add(new SelectCommandPatternManager());
		commands.add(new UpdateCommandPatternManager());
		commands.add(new UseCommandPatternManager());
		commands.add(new AlterAddCommandPatternManager());
		commands.add(new AlterDropCommandPatternManager());
		commands.add(new UnionCommandPatternManager());
	}
	@Override
	public final QueryAction getCommandAction(final String command)
			throws WrongSyntaxException {
		for (int i = 0; i < commands.size(); i++) {
			if (commands.get(i).isValid(command)) {
				return commands.get(i).getAction();
			}
		}
		throw new WrongSyntaxException();
	}

	@Override
	public final QueryBuildUnit generateBuildUnit(final String command)
			throws UnknownColumnDatatypeException, WrongSyntaxException {
		for (int i = 0; i < commands.size(); i++) {
			if (commands.get(i).isValid(command)) {
				return commands.get(i).buildBuildUnit(command);
			}
		}
		throw new WrongSyntaxException();
	}

}
