package lt.gt.galaktika.web.various;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class GalaktikaSpringPrincipal extends org.springframework.security.core.userdetails.User
{
	private static final long serialVersionUID = 4355753995528067506L;
	
	private long userId;

	public GalaktikaSpringPrincipal () {
		super("NULL", null, false, false, false, false, null );
	}
	public GalaktikaSpringPrincipal(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities)
	{
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

	public long getUserId ()
	{
		return userId;
	}

	public void setUserId ( long userId )
	{
		this.userId = userId;
	}
	
}
