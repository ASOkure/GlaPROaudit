package uk.org.bsped.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumns;
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
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;
import uk.org.bsped.util.PatientComparator;

@Entity
@NamedQueries({@javax.persistence.NamedQuery(name="findAllAuditRecords", query="select myAuditRecord from AuditRecord myAuditRecord"), @javax.persistence.NamedQuery(name="findAuditRecordByAuditRecordId", query="select myAuditRecord from AuditRecord myAuditRecord where myAuditRecord.auditRecordId = ?1"), @javax.persistence.NamedQuery(name="findAuditRecordByPrimaryKey", query="select myAuditRecord from AuditRecord myAuditRecord where myAuditRecord.auditRecordId = ?1"), @javax.persistence.NamedQuery(name="findAuditRecordByRequestTimestamp", query="select myAuditRecord from AuditRecord myAuditRecord where myAuditRecord.requestTimestamp = ?1"), @javax.persistence.NamedQuery(name="findAuditRecordByResponseTimestamp", query="select myAuditRecord from AuditRecord myAuditRecord where myAuditRecord.responseTimestamp = ?1"), @javax.persistence.NamedQuery(name="findAuditRecordByStatus", query="select myAuditRecord from AuditRecord myAuditRecord where myAuditRecord.status = ?1"), @javax.persistence.NamedQuery(name="findAuditRecordByStatusContaining", query="select myAuditRecord from AuditRecord myAuditRecord where myAuditRecord.status like ?1")})
@Table(catalog="ghaudit", name="audit_record")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace="ghaudit_data/uk/org/bsped/model", name="AuditRecord")
@XmlRootElement(namespace="ghaudit_data/uk/org/bsped/model")
public class AuditRecord
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  @Column(name="audit_record_id", nullable=false)
  @Basic(fetch=FetchType.EAGER)
  @Id
  @GeneratedValue
  @XmlElement
  Integer auditRecordId;
  @ManyToOne(fetch=FetchType.EAGER)
  @JoinColumns({@javax.persistence.JoinColumn(name="audit_id", referencedColumnName="audit_id")})
  @XmlTransient
  Audit audit;
  @ManyToOne(fetch=FetchType.EAGER)
  @JoinColumns({@javax.persistence.JoinColumn(name="centre_id", referencedColumnName="centre_id")})
  @XmlTransient
  Centre centre;
  @OneToMany(mappedBy="auditRecord", cascade={javax.persistence.CascadeType.REMOVE}, fetch=FetchType.LAZY)
  @XmlElement(name="", namespace="")
  @Sort(type=SortType.COMPARATOR, comparator=PatientComparator.class)
  Set<Patient> patients;
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name="request_timestamp")
  @Basic(fetch=FetchType.EAGER)
  @XmlElement
  Calendar requestTimestamp;
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name="response_timestamp")
  @Basic(fetch=FetchType.EAGER)
  @XmlElement
  Calendar responseTimestamp;
  @Basic(fetch=FetchType.EAGER)
  @XmlElement
  @Column(columnDefinition="enum('PENDING', 'REQUESTED','RESPONDED','UNKNOWN')")
  @Enumerated(EnumType.STRING)
  AuditRecordStatus status;
  
  public static enum AuditRecordStatus
  {
    PENDING,  REQUESTED,  RESPONDED;
  }
  
  public Integer getAuditRecordId()
  {
    return this.auditRecordId;
  }
  
  public void setAuditRecordId(Integer auditRecordId) {}
  
  public AuditRecordStatus getStatus()
  {
    return this.status;
  }
  
  public void setStatus(AuditRecordStatus status)
  {
    this.status = status;
  }
  
  public Calendar getRequestTimestamp()
  {
    return this.requestTimestamp;
  }
  
  public void setRequestTimestamp(Calendar requestTimestamp)
  {
    this.requestTimestamp = requestTimestamp;
  }
  
  public Calendar getResponseTimestamp()
  {
    return this.responseTimestamp;
  }
  
  public void setResponseTimestamp(Calendar responseTimestamp)
  {
    this.responseTimestamp = responseTimestamp;
  }
  
  public Audit getAudit()
  {
    return this.audit;
  }
  
  public void setAudit(Audit audit)
  {
    this.audit = audit;
  }
  
  public Centre getCentre()
  {
    return this.centre;
  }
  
  public void setCentre(Centre centre)
  {
    this.centre = centre;
  }
  
  public Set<Patient> getPatients()
  {
    return this.patients;
  }
  
  public void setPatients(Set<Patient> patients)
  {
    this.patients = patients;
  }
  
  public void copy(AuditRecord that)
  {
    setAuditRecordId(that.getAuditRecordId());
    setStatus(that.getStatus());
    setRequestTimestamp(that.getRequestTimestamp());
    setResponseTimestamp(that.getResponseTimestamp());
    setAudit(that.getAudit());
    setCentre(that.getCentre());
    setPatients(new TreeSet(that.getPatients()));
  }
  
  public String toString()
  {
    return 
    
      MoreObjects.toStringHelper(this).add("auditRecordId", this.auditRecordId).add("centre", this.centre).add("requestTimestamp", this.requestTimestamp).add("responseTimestamp", this.responseTimestamp).add("status", this.status).toString();
  }
}