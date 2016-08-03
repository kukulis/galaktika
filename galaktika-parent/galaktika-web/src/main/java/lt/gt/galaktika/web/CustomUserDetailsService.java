package lt.gt.galaktika.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lt.gt.galaktika.model.dao.IUserDao;
import lt.gt.galaktika.model.entity.noturn.User;
import lt.gt.galaktika.web.various.GalaktikaSpringPrincipal;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService
{


	@Autowired
	private IUserDao userDao;

	// @Transactional(readOnly=true)
	public UserDetails loadUserByUsername ( String ssoId ) throws UsernameNotFoundException
	{
		System.out.println("ssoId : " + ssoId);

		
		User user = userDao.getByLogin(ssoId);
		if (user == null)
		{
			System.out.println("User not found");
			throw new UsernameNotFoundException("Username not found");
		}
		else
		{
			GalaktikaSpringPrincipal gsp = new GalaktikaSpringPrincipal(ssoId, user.getPassword(), true, true, true,
					true, getGrantedAuthorities());
			System.out.println ( "Setting user id="+user.getId() );
			gsp.setUserId(user.getId());
			return gsp;
		}
//		 return new org.springframework.security.core.userdetails.User(ssoId,
//		 "slaptas", true, true, true, true,
//		 getGrantedAuthorities());
	}

	// private List<GrantedAuthority> getGrantedAuthorities(User user){
	private List<GrantedAuthority> getGrantedAuthorities ()
	{
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		// for(UserProfile userProfile : user.getUserProfiles()){
		// System.out.println("UserProfile : "+userProfile);
		// authorities.add(new
		// SimpleGrantedAuthority("ROLE_"+userProfile.getType()));
		// }
		// System.out.print("authorities :"+authorities);

		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		return authorities;
	}

}