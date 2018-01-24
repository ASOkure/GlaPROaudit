package uk.org.bsped.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@NamedQueries({@javax.persistence.NamedQuery(name="findAllCentres", query="select myCentre from Centre myCentre"), @javax.persistence.NamedQuery(name="findCentreByCity", query="select myCentre from Centre myCentre where myCentre.city = ?1"), @javax.persistence.NamedQuery(name="findCentreByCityContaining", query="select myCentre from Centre myCentre where myCentre.city like ?1"), @javax.persistence.NamedQuery(name="findCentreByCentreId", query="select myCentre from Centre myCentre where myCentre.centreId = ?1"), @javax.persistence.NamedQuery(name="findCentreByCentreName", query="select myCentre from Centre myCentre where myCentre.centreName = ?1"), @javax.persistence.NamedQuery(name="findCentreByCentreNameContaining", query="select myCentre from Centre myCentre where myCentre.centreName like ?1"), @javax.persistence.NamedQuery(name="findCentreByCountry", query="select myCentre from Centre myCentre where myCentre.country = ?1"), @javax.persistence.NamedQuery(name="findCentreByCountryContaining", query="select myCentre from Centre myCentre where myCentre.country like ?1"), @javax.persistence.NamedQuery(name="findCentreByPrimaryKey", query="select myCentre from Centre myCentre where myCentre.centreId = ?1"), @javax.persistence.NamedQuery(name="findCentreByRegion", query="select myCentre from Centre myCentre where myCentre.region = ?1"), @javax.persistence.NamedQuery(name="findCentreByRegionContaining", query="select myCentre from Centre myCentre where myCentre.region like ?1")})
@Table(catalog="ghaudit", name="centre")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace="ghaudit/uk/org/bsped/ghaudit/domain", name="Centre")
@XmlRootElement(namespace="ghaudit/uk/org/bsped/ghaudit/domain")
public class Centre
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  @Column(name="centre_id", nullable=false)
  @Basic(fetch=FetchType.EAGER)
  @Id
  @GeneratedValue
  @XmlElement
  Integer centreId;
  @Column(name="centre_name", length=500)
  @Basic(fetch=FetchType.EAGER)
  @XmlElement
  String centreName;
  @Column(name="city", length=300)
  @Basic(fetch=FetchType.EAGER)
  @XmlElement
  String city;
  @Column(name="region", length=300)
  @Basic(fetch=FetchType.EAGER)
  @XmlElement
  String region;
  @Column(name="country", length=300)
  @Basic(fetch=FetchType.EAGER)
  @XmlElement
  String country;
  @Column(name="centre_lead_name", length=300)
  @Basic(fetch=FetchType.EAGER)
  @XmlElement
  String centreLeadName;
  @Column(name="centre_lead_email", length=300)
  @Basic(fetch=FetchType.EAGER)
  @XmlElement
  String centreLeadEmail;
  @Column(name="audit_lead_name", length=300)
  @Basic(fetch=FetchType.EAGER)
  @XmlElement
  String auditLeadName;
  @Column(name="audit_lead_email", length=300)
  @Basic(fetch=FetchType.EAGER)
  @XmlElement
  String auditLeadEmail;
  
  public Integer getCentreId()
  {
    return this.centreId;
  }
  
  public void setCentreId(Integer centreId)
  {
    this.centreId = centreId;
  }
  
  public String getCentreName()
  {
    return this.centreName;
  }
  
  public void setCentreName(String centreName)
  {
    this.centreName = centreName;
  }
  
  public String getCity()
  {
    return this.city;
  }
  
  public void setCity(String city)
  {
    this.city = city;
  }
  
  public String getRegion()
  {
    return this.region;
  }
  
  public void setRegion(String region)
  {
    this.region = region;
  }
  
  public String getCountry()
  {
    return this.country;
  }
  
  public void setCountry(String country)
  {
    this.country = country;
  }
  
  public String getCentreLeadName()
  {
    return this.centreLeadName;
  }
  
  public void setCentreLeadName(String centreLeadName)
  {
    this.centreLeadName = centreLeadName;
  }
  
  public String getCentreLeadEmail()
  {
    return this.centreLeadEmail;
  }
  
  public void setCentreLeadEmail(String centreLeadEmail)
  {
    this.centreLeadEmail = centreLeadEmail;
  }
  
  public String getAuditLeadName()
  {
    return this.auditLeadName;
  }
  
  public void setAuditLeadName(String auditLeadName)
  {
    this.auditLeadName = auditLeadName;
  }
  
  public String getAuditLeadEmail()
  {
    return this.auditLeadEmail;
  }
  
  public void setAuditLeadEmail(String auditLeadEmail)
  {
    this.auditLeadEmail = auditLeadEmail;
  }
  
  public void copy(Centre that)
  {
    setCentreId(that.getCentreId());
    setCentreName(that.getCentreName());
    setCity(that.getCity());
    setRegion(that.getRegion());
    setCountry(that.getCountry());
    setCentreLeadName(that.getCentreLeadName());
    setCentreLeadEmail(that.getCentreLeadEmail());
    setAuditLeadName(that.getAuditLeadName());
    setAuditLeadEmail(that.getAuditLeadEmail());
  }
  
  public String toString()
  {
    return 
    
      MoreObjects.toStringHelper(this).add("centreId", this.centreId).add("centreName", this.centreName).add("city", this.city).add("region", this.region).add("country", this.country).add("centreLeadName", this.centreLeadName).add("centreLeadEmail", this.centreLeadEmail).add("auditLeadName", this.auditLeadName).add("auditLeadEmail", this.auditLeadEmail).toString();
  }
}