package uk.org.bsped.domain;

import java.util.Set;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import uk.org.bsped.model.Authorities;
import uk.org.bsped.model.Users;

public class CurrentUser
  extends User
{
  private Users user;
  
  public CurrentUser(Users user)
  {
    super(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList(new String[] { user.getAuthoritieses().toString() }));
    this.user = user;
  }
  
  public Users getUser()
  {
    return this.user;
  }
  
  public String getName()
  {
    return this.user.getName();
  }
  
  public String getUsername()
  {
    return this.user.getUsername();
  }
  
  public Integer getId()
  {
    return this.user.getId();
  }
  
  public Set<Authorities> getRole()
  {
    return this.user.getAuthoritieses();
  }
  
  public String toString()
  {
    return 
    
      "CurrentUser{user=" + this.user + "} " + super.toString();
  }
}
