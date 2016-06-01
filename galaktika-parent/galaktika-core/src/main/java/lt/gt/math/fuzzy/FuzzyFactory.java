package lt.gt.math.fuzzy;

public class FuzzyFactory
{
	public final static String BATTLE_FUNCTION = "BATTLE_FUNCTION";

	private static class Lazy
	{
		static FuzzyFactory INSTANCE = new FuzzyFactory();
	}

	private FuzzyFactory()
	{
	}

	public static FuzzyFactory getInstance ()
	{
		return Lazy.INSTANCE;
	}

	public FuzzyFunction create ( String which )
	{
		if (BATTLE_FUNCTION.equals(which))
			return createBattleFunction();
		else 
			return null;
	}

	public FuzzyFunction createBattleFunction ()
	{
		FuzzyFunction ff = new FuzzyFunction();

		ff.addPoint(new FuzzyPoint(0.25, 0)); // if attack 4 times less than deffence - 0% probability
		ff.addPoint(new FuzzyPoint(1, 0.5)); // if attack is equal to deffence - 50% probability
		ff.addPoint(new FuzzyPoint(4, 1)); // if attack 4 times greater than deffence - 100 % probability
		return ff;
	}
}
