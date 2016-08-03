package lt.gt.galaktika.web.various;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import lt.gt.galaktika.model.dao.INationDao;
import lt.gt.galaktika.model.entity.noturn.DNation;

@Component
@Scope("session")
public class UserSessionData
{
	@Autowired
	private INationDao nationDao;
	
	private DNation nation; // used for caching

	public DNation getNation ()
	{
		if ( nation == null )
			loadNation();
		return nation;
	}

	public void setNation ( DNation nation )
	{
		this.nation = nation;
	}
	
	private void loadNation () {
		List<DNation> nations = nationDao.getNationByUser(getGalaktikaUser().getUserId() );
		if ( nations.size() > 0 )
			this.nation=nations.get(0);
		else 
			this.nation = new DNation(); // null nation
	}
	
	public String getUser () {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      String name = auth.getName(); 
	      return name; 
	}
	
	public GalaktikaSpringPrincipal getGalaktikaUser () {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 Object principal = auth.getPrincipal();
		 if ( principal instanceof GalaktikaSpringPrincipal ) {
			 System.out.println ( "This is galaktika spring principal" );
			 GalaktikaSpringPrincipal gsp = (GalaktikaSpringPrincipal) principal;
			 return gsp;
		 }
		 else {
			 System.out.println ( "This is not galaktika spring principal" );
			 return new GalaktikaSpringPrincipal();
		 }
	}
}
