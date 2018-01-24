package uk.org.bsped.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;
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
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import org.hibernate.annotations.BatchSize;

@Entity
@NamedQueries({@javax.persistence.NamedQuery(name="findAllAudits", query="select myAudit from Audit myAudit"), @javax.persistence.NamedQuery(name="findAuditByAuditId", query="select myAudit from Audit myAudit where myAudit.auditId = ?1"), @javax.persistence.NamedQuery(name="findAuditByName", query="select myAudit from Audit myAudit where myAudit.name = ?1"), @javax.persistence.NamedQuery(name="findAuditByCreateTimestamp", query="select myAudit from Audit myAudit where myAudit.createTimestamp = ?1"), @javax.persistence.NamedQuery(name="findAuditByPrimaryKey", query="select myAudit from Audit myAudit where myAudit.auditId = ?1"), @javax.persistence.NamedQuery(name="findAuditByTextNote", query="select myAudit from Audit myAudit where myAudit.note = ?1")})
@Table(catalog="ghaudit", name="audit")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace="ghaudit_data/uk/org/bsped/ghaudit/model", name="Audit")
@XmlRootElement(namespace="ghaudit_data/uk/org/bsped/ghaudit/model")
public class Audit
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  @Column(name="audit_id", nullable=false)
  @Basic(fetch=FetchType.EAGER)
  @Id
  @GeneratedValue
  @XmlElement
  Integer auditId;
  @Column(name="name", length=500)
  @Basic(fetch=FetchType.EAGER)
  @XmlElement
  String name;
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name="create_timestamp")
  @Basic(fetch=FetchType.EAGER)
  @XmlElement
  Calendar createTimestamp;
  @Column(name="note", columnDefinition="TEXT")
  @Basic(fetch=FetchType.EAGER)
  @Lob
  @XmlElement
  String note;
  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumns({@javax.persistence.JoinColumn(name="create_user", referencedColumnName="id")})
  @XmlTransient
  Users creator;
  @OneToMany(mappedBy="audit", cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.EAGER)
  @XmlElement(name="", namespace="")
  @BatchSize(size=200)
  Set<AuditRecord> auditRecords;
  
  public Integer getAuditId()
  {
    return this.auditId;
  }
  
  public void setAuditId(Integer auditId)
  {
    this.auditId = auditId;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public Calendar getCreateTimestamp()
  {
    return this.createTimestamp;
  }
  
  public void setCreateTimestamp(Calendar createTimestamp)
  {
    this.createTimestamp = createTimestamp;
  }
  
  public String getNote()
  {
    return this.note;
  }
  
  public void setNote(String note)
  {
    this.note = note;
  }
  
  public Users getCreator()
  {
    return this.creator;
  }
  
  public void setCreator(Users creator)
  {
    this.creator = creator;
  }
  
  public void setAuditRecords(Set<AuditRecord> auditRecords)
  {
    this.auditRecords = auditRecords;
  }
  
  public void copy(Audit that)
  {
    setAuditId(that.getAuditId());
    setCreateTimestamp(that.getCreateTimestamp());
    setNote(that.getNote());
    setCreator(that.getCreator());
    setAuditRecords(new LinkedHashSet(that.getAuditRecords()));
  }
  
  public Set<AuditRecord> getAuditRecords()
  {
    if (this.auditRecords == null) {
      this.auditRecords = new LinkedHashSet();
    }
    return this.auditRecords;
  }
  
  public String toString()
  {
    return 
    
      MoreObjects.toStringHelper(this).add("auditId", this.auditId).add("name", this.name).add("createTimestamp", this.createTimestamp).add("note", this.note).add("creator", this.creator).toString();
  }
}