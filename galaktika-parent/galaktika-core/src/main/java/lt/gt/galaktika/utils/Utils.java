package lt.gt.galaktika.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;

import lt.gt.galaktika.core.Technologies;
import lt.gt.galaktika.core.battle.BattleReport;
import lt.gt.galaktika.core.battle.BattleReportRound;
import lt.gt.galaktika.core.battle.BattleReportShot;
import lt.gt.galaktika.core.exception.GalaktikaRuntimeException;

public class Utils
{
	public final static double EPSILON = 0.000_001;
	public static void printReport ( BattleReport report )
	{
		for (BattleReportRound round : report.getRounds())
		{
			System.out.println("round:" + round.getRoundNumber());
			for (BattleReportShot shot : round.getShots())
			{
				System.out.println("Ship " + shot.getAttackerShip() + " shots at " + shot.getDefenderShip()
						+ " destroyed:" + shot.isDestroyed());
			}
		}
	}

	public static long value ( Long l, long def )
	{
		if (l == null)
			return def;
		else
			return l.longValue();
	}

	public static <E> Map<E, E> createMap ( Set<E> values )
	{
		Map<E, E> map = new HashMap<>();
		values.forEach(v -> map.put(v, v));
		return map;
	}

	public static <K, V> Map<K, V> cloneMapValues ( Map<K, V> map )
	{
		Map<K, V> resultMap = new HashMap<>();
		map.forEach( ( k, v ) -> {
			try
			{
				resultMap.put(k, (V) BeanUtils.cloneBean(v));
			} catch (Exception e)
			{
				throw new GalaktikaRuntimeException(e);
			}
		});
		return resultMap;
	}
}
