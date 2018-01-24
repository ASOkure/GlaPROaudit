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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@NamedQueries({@javax.persistence.NamedQuery(name="findAllEmailTemplates", query="select et from EmailTemplate et"), @javax.persistence.NamedQuery(name="findEmailTemplateById", query="select et from EmailTemplate et where et.id = ?1"), @javax.persistence.NamedQuery(name="findEmailTemplateByTemplate", query="select et from EmailTemplate et where et.template = ?1"), @javax.persistence.NamedQuery(name="findEmailTemplateByName", query="select et from EmailTemplate et where et.name = ?1"), @javax.persistence.NamedQuery(name="findEmailTemplateByNameContaining", query="select et from EmailTemplate et where et.name like ?1"), @javax.persistence.NamedQuery(name="findEmailTemplateByPrimaryKey", query="select et from EmailTemplate et where et.id = ?1")})
@Table(catalog="ghaudit", name="email_template")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace="ghaudit_data/uk/org/bsped/model", name="EmailTemplate")
@XmlRootElement(namespace="ghaudit_data/uk/org/bsped/model")
public class EmailTemplate
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  @Column(name="id", nullable=false)
  @Basic(fetch=FetchType.EAGER)
  @Id
  @GeneratedValue
  @XmlElement
  Integer id;
  @Column(name="name", length=300)
  @Basic(fetch=FetchType.EAGER)
  @XmlElement
  String name;
  @Column(name="subject", columnDefinition="TEXT")
  @Basic(fetch=FetchType.EAGER)
  @Lob
  @XmlElement
  String subject;
  @Column(name="template", columnDefinition="TEXT")
  @Basic(fetch=FetchType.EAGER)
  @Lob
  @XmlElement
  String template;
  
  public Integer getId()
  {
    return this.id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getSubject()
  {
    return this.subject;
  }
  
  public void setSubject(String subject)
  {
    this.subject = subject;
  }
  
  public String getTemplate()
  {
    return this.template;
  }
  
  public void setTemplate(String template)
  {
    this.template = template;
  }
  
  public void copy(EmailTemplate that)
  {
    setId(that.getId());
    setName(that.getName());
    setSubject(getSubject());
    setTemplate(that.getTemplate());
  }
  
  public boolean equals(Object obj)
  {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof EmailTemplate)) {
      return false;
    }
    EmailTemplate equalCheck = (EmailTemplate)obj;
    if (((this.id == null) && (equalCheck.id != null)) || ((this.id != null) && (equalCheck.id == null))) {
      return false;
    }
    if ((this.id != null) && (!this.id.equals(equalCheck.id))) {
      return false;
    }
    return true;
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
    return 
    
      MoreObjects.toStringHelper(this).add("id", this.id).add("name", this.name).add("subject", this.subject).add("template", this.template).toString();
  }
}
