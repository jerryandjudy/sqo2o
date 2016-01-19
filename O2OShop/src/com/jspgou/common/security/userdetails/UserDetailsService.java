package com.jspgou.common.security.userdetails;

import org.springframework.dao.DataAccessException;

import com.jspgou.common.security.UsernameNotFoundException;

/**
 * Defines an interface for implementations that wish to provide data access
 * services to the {@link DaoAuthenticationProvider}.
 * 
 * <p>
 * The interface requires only one read-only method, which simplifies support of
 * new data access strategies.
 * </p>
 * 
 * @author Ben Alex
 * @version $Id: UserDetailsService.java,v 1.1 2014/08/11 01:46:24 Administrator Exp $
 * This class should preserve.
 * @preserve
*/
public interface UserDetailsService {
	// ~ Methods
	// ========================================================================================================

	/**
	 * Locates the user based on the username. In the actual implementation, the
	 * search may possibly be case insensitive, or case insensitive depending on
	 * how the implementaion instance is configured. In this case, the
	 * <code>UserDetails</code> object that comes back may have a username that
	 * is of a different case than what was actually requested..
	 * 
	 * @param username
	 *            the username presented to the
	 *            {@link DaoAuthenticationProvider}
	 * 
	 * @return a fully populated user record (never <code>null</code>)
	 * 
	 * @throws UsernameNotFoundException
	 *             if the user could not be found or the user has no
	 *             GrantedAuthority
	 * @throws DataAccessException
	 *             if user could not be found for a repository-specific reason
	 */
	UserDetails loadUser(Long userId, String username)
			throws UsernameNotFoundException, DataAccessException;
}
