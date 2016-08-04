package lt.gt.galaktika.model.test.independent;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import lt.gt.galaktika.model.entity.noturn.DShip;
import lt.gt.galaktika.model.entity.turn.DShipGroup;
import lt.gt.galaktika.utils.Diff;

public class TestShipGroupDiff
{
	@Test
	public void testShipGroupArraysDiff ()
	{
		System.out.println("testShipGroupArraysDiff called");
		// create two arrays of ship groups
		// make diff object
		// validate result

		DShip pirmas = new DShip(1, "pirmas");
		DShip antras = new DShip(2, "antras");
		DShip trecias = new DShip(3, "trecias");
		DShip ketvirtas = new DShip(4, "ketvirtas");

		List<DShipGroup> groups1 = Arrays.asList(new DShipGroup(pirmas ), new DShipGroup(antras ),
				new DShipGroup(trecias));
		List<DShipGroup> groups2 = Arrays.asList(new DShipGroup(102, antras), new DShipGroup(103, trecias),
				new DShipGroup(104, ketvirtas));

		Diff<DShipGroup> diff = new Diff<DShipGroup>().diff(groups1, groups2);
		System.out.println("First only:");
		printGroupsSet(diff.getFirstOnly(), System.out);
		System.out.println("Second only:");
		printGroupsSet(diff.getSecondOnly(), System.out);
		System.out.println("Both:");
		printGroupsSet(diff.getBothFirst(), System.out);
		System.out.println("Both second:");
		printGroupsSet(diff.getBothSecond(), System.out);

		Assert.assertTrue( diff.getFirstOnly().equals(new HashSet<>(Arrays.asList(new DShipGroup(pirmas)))) );
		Assert.assertTrue( diff.getSecondOnly().equals(new HashSet<>(Arrays.asList(new DShipGroup(ketvirtas)))));
		Assert.assertTrue( diff.getBothFirst().equals(new HashSet<>(Arrays.asList(new DShipGroup(antras), new DShipGroup(trecias)))));

	}

	private void printGroupsSet ( Collection<DShipGroup> groups, PrintStream out )
	{
		groups.forEach(g -> out.print("((" + g.getShipGroupId()+") " + g.getShip().getId() + "," + g.getShip().getName() + ") "));
		out.println();
	}

}
