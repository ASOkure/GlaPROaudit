package uk.org.bsped.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@NamedQueries({@javax.persistence.NamedQuery(name="findAllUserss", query="select myUsers from Users myUsers"), @javax.persistence.NamedQuery(name="findUsersByCentre", query="select myUsers from Users myUsers where myUsers.centre = ?1"), @javax.persistence.NamedQuery(name="findUsersByCentreContaining", query="select myUsers from Users myUsers where myUsers.centre like ?1"), @javax.persistence.NamedQuery(name="findUsersByCountry", query="select myUsers from Users myUsers where myUsers.country = ?1"), @javax.persistence.NamedQuery(name="findUsersByCountryContaining", query="select myUsers from Users myUsers where myUsers.country like ?1"), @javax.persistence.NamedQuery(name="findUsersByEmail", query="select myUsers from Users myUsers where myUsers.email = ?1"), @javax.persistence.NamedQuery(name="findUsersByEmailContaining", query="select myUsers from Users myUsers where myUsers.email like ?1"), @javax.persistence.NamedQuery(name="findUsersByEnabled", query="select myUsers from Users myUsers where myUsers.enabled = ?1"), @javax.persistence.NamedQuery(name="findUsersById", query="select myUsers from Users myUsers where myUsers.id = ?1"), @javax.persistence.NamedQuery(name="findUsersByLastVisit", query="select myUsers from Users myUsers where myUsers.lastVisit = ?1"), @javax.persistence.NamedQuery(name="findUsersByLoginCounter", query="select myUsers from Users myUsers where myUsers.loginCounter = ?1"), @javax.persistence.NamedQuery(name="findUsersByName", query="select myUsers from Users myUsers where myUsers.name = ?1"), @javax.persistence.NamedQuery(name="findUsersByNameContaining", query="select myUsers from Users myUsers where myUsers.name like ?1"), @javax.persistence.NamedQuery(name="findUsersByPassword", query="select myUsers from Users myUsers where myUsers.password = ?1"), @javax.persistence.NamedQuery(name="findUsersByPasswordContaining", query="select myUsers from Users myUsers where myUsers.password like ?1"), @javax.persistence.NamedQuery(name="findUsersByPrimaryKey", query="select myUsers from Users myUsers where myUsers.id = ?1"), @javax.persistence.NamedQuery(name="findUsersByRegDate", query="select myUsers from Users myUsers where myUsers.regDate = ?1"), @javax.persistence.NamedQuery(name="findUsersByTel", query="select myUsers from Users myUsers where myUsers.tel = ?1"), @javax.persistence.NamedQuery(name="findUsersByTelContaining", query="select myUsers from Users myUsers where myUsers.tel like ?1"), @javax.persistence.NamedQuery(name="findUsersByUsername", query="select myUsers from Users myUsers where myUsers.username = ?1"), @javax.persistence.NamedQuery(name="findUsersByUsernameContaining", query="select myUsers from Users myUsers where myUsers.username like ?1")})
@Table(catalog="ghaudit", name="users")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace="ghaudit/uk/org/bsped/model", name="Users")
@XmlRootElement(namespace="ghaudit/uk/org/bsped/model")
public class Users
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  @Column(name="id", nullable=false)
  @Basic(fetch=FetchType.EAGER)
  @Id
  @GeneratedValue
  @XmlElement
  Integer id;
  @Column(name="username")
  @Basic(fetch=FetchType.EAGER)
  @XmlElement
  String username;
  @Column(name="country", length=100)
  @Basic(fetch=FetchType.EAGER)
  @XmlElement
  String country;
  @Column(name="centre", length=200)
  @Basic(fetch=FetchType.EAGER)
  @XmlElement
  String centre;
  @Column(name="email", length=200)
  @Basic(fetch=FetchType.EAGER)
  @XmlElement
  String email;
  @Column(name="enabled")
  @Basic(fetch=FetchType.EAGER)
  @XmlElement
  Boolean enabled;
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name="last_visit")
  @Basic(fetch=FetchType.EAGER)
  @XmlElement
  Calendar lastVisit;
  @Column(name="login_counter")
  @Basic(fetch=FetchType.EAGER)
  @XmlElement
  Integer loginCounter;
  @Column(name="name", length=200)
  @Basic(fetch=FetchType.EAGER)
  @XmlElement
  String name;
  @Column(name="password")
  @Basic(fetch=FetchType.EAGER)
  @XmlElement
  String password;
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name="reg_date")
  @Basic(fetch=FetchType.EAGER)
  @XmlElement
  Calendar regDate;
  @Column(name="tel", length=25)
  @Basic(fetch=FetchType.EAGER)
  @XmlElement
  String tel;
  @ManyToMany(mappedBy="userses", fetch=FetchType.EAGER)
  @XmlElement(name="", namespace="")
  Set<Authorities> authoritieses;
  
  public Integer getId()
  {
    return this.id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  public Boolean getEnabled()
  {
    return this.enabled;
  }
  
  public void setEnabled(Boolean enabled)
  {
    this.enabled = enabled;
  }
  
  public String getPassword()
  {
    return this.password;
  }
  
  public void setPassword(String password)
  {
    this.password = password;
  }
  
  public String getUsername()
  {
    return this.username;
  }
  
  public void setUsername(String username)
  {
    this.username = username;
  }
  
  public String getEmail()
  {
    return this.email;
  }
  
  public void setEmail(String email)
  {
    this.email = email;
  }
  
  public Calendar getRegDate()
  {
    return this.regDate;
  }
  
  public void setRegDate(Calendar regDate)
  {
    this.regDate = regDate;
  }
  
  public Calendar getLastVisit()
  {
    return this.lastVisit;
  }
  
  public void setLastVisit(Calendar lastVisit)
  {
    this.lastVisit = lastVisit;
  }
  
  public Integer getLoginCounter()
  {
    return this.loginCounter;
  }
  
  public void setLoginCounter(Integer loginCounter)
  {
    this.loginCounter = loginCounter;
  }
  
  public String getCountry()
  {
    return this.country;
  }
  
  public void setCountry(String country)
  {
    this.country = country;
  }
  
  public String getCentre()
  {
    return this.centre;
  }
  
  public void setCentre(String centre)
  {
    this.centre = centre;
  }
  
  public String getTel()
  {
    return this.tel;
  }
  
  public void setTel(String tel)
  {
    this.tel = tel;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public Set<Authorities> getAuthorities()
  {
    return this.authoritieses;
  }
  
  public void setAuthoritieses(Set<Authorities> authoritieses)
  {
    this.authoritieses = authoritieses;
  }
  
  public void copy(Users that)
  {
    setId(that.getId());
    setEnabled(that.getEnabled());
    setPassword(that.getPassword());
    setUsername(that.getUsername());
    setName(that.getName());
    setEmail(that.getEmail());
    setRegDate(that.getRegDate());
    setLastVisit(that.getLastVisit());
    setLoginCounter(that.getLoginCounter());
    setCountry(that.getCountry());
    setCentre(that.getCentre());
    setTel(that.getTel());
    setAuthoritieses(new LinkedHashSet(that.getAuthoritieses()));
  }
  
  public boolean equals(Object obj)
  {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof Users)) {
      return false;
    }
    Users equalCheck = (Users)obj;
    if (((this.id == null) && (equalCheck.id != null)) || ((this.id != null) && (equalCheck.id == null))) {
      return false;
    }
    if ((this.id != null) && (!this.id.equals(equalCheck.id))) {
      return false;
    }
    return true;
  }
  
  public Set<Authorities> getAuthoritieses()
  {
    if (this.authoritieses == null) {
      this.authoritieses = new LinkedHashSet();
    }
    return this.authoritieses;
  }
  
  public int hashCode()
  {
    int prime = 31;
    int result = 1;
    result = 31 * result + (this.id == null ? 0 : this.id.hashCode());
    return result;
  }
  
  public String toString()
  {
    StringBuilder buffer = new StringBuilder();
    
    buffer.append("id=[").append(this.id).append("] ");
    buffer.append("enabled=[").append(this.enabled).append("] ");
    buffer.append("username=[").append(this.username).append("] ");
    buffer.append("name=[").append(this.name).append("] ");
    buffer.append("email=[").append(this.email).append("] ");
    buffer.append("regDate=[").append(this.regDate == null ? null : this.regDate.getTime()).append("] ");
    buffer.append("lastVisit=[").append(this.lastVisit).append("] ");
    buffer.append("loginCounter=[").append(this.loginCounter).append("] ");
    
    return buffer.toString();
  }
}
