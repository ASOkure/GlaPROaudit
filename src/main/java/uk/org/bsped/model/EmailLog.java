package uk.org.bsped.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;
import java.io.Serializable;
import java.util.Calendar;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import org.hibernate.annotations.NamedNativeQueries;

@Entity
@NamedQueries({@javax.persistence.NamedQuery(name="findAllEmailLogs", query="select el from EmailLog el"), @javax.persistence.NamedQuery(name="findEmailLogById", query="select el from EmailLog el where el.id = ?1"), @javax.persistence.NamedQuery(name="findEmailLogBySubject", query="select el from EmailLog el where el.subject = ?1"), @javax.persistence.NamedQuery(name="findEmailLogByCreator", query="select el from EmailLog el where el.creator = ?1"), @javax.persistence.NamedQuery(name="findEmailLogsByAuditRecordId", query="select el from EmailLog el where el.auditRecordId = ?1"), @javax.persistence.NamedQuery(name="findLatestEmailLogByAuditRecordId", query="select el from EmailLog el where el.auditRecordId = ?1 order by el.id desc"), @javax.persistence.NamedQuery(name="findEmailLogByPrimaryKey", query="select el from EmailLog el where el.id = ?1")})
@NamedNativeQueries({@org.hibernate.annotations.NamedNativeQuery(name="findLatestEmailLogByAuditRecordIdNative", query="select * from email_log el where el.audit_record_id = :auditRecordId order by el.timestamp desc", resultClass=EmailLog.class)})
@Table(catalog="ghaudit", name="email_log")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace="ghaudit_data/uk/org/bsped/model", name="EmailLog")
@XmlRootElement(namespace="ghaudit_data/uk/org/bsped/model")
public class EmailLog
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  @Column(name="id", nullable=false)
  @Basic(fetch=FetchType.EAGER)
  @Id
  @GeneratedValue
  @XmlElement
  Integer id;
  @Column(name="counter")
  @Basic(fetch=FetchType.EAGER)
  @XmlElement
  Integer counter;
  @Column(name="subject", columnDefinition="TEXT")
  @Basic(fetch=FetchType.EAGER)
  @Lob
  @XmlElement
  String subject;
  @Column(name="content", columnDefinition="TEXT")
  @Basic(fetch=FetchType.EAGER)
  @Lob
  @XmlElement
  String content;
  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumns({@javax.persistence.JoinColumn(name="creator", referencedColumnName="id")})
  @XmlTransient
  Users creator;
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name="timestamp")
  @Basic(fetch=FetchType.EAGER)
  @XmlElement
  Calendar timestamp;
  @Column(name="audit_record_id")
  @Basic(fetch=FetchType.EAGER)
  @XmlElement
  Integer auditRecordId;
  
  public Integer getId()
  {
    return this.id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  public Integer getCounter()
  {
    return this.counter;
  }
  
  public void setCounter(Integer counter)
  {
    this.counter = counter;
  }
  
  public String getSubject()
  {
    return this.subject;
  }
  
  public void setSubject(String subject)
  {
    this.subject = subject;
  }
  
  public String getContent()
  {
    return this.content;
  }
  
  public void setContent(String content)
  {
    this.content = content;
  }
  
  public Users getCreator()
  {
    return this.creator;
  }
  
  public void setCreator(Users creator)
  {
    this.creator = creator;
  }
  
  public Calendar getTimestamp()
  {
    return this.timestamp;
  }
  
  public void setTimestamp(Calendar timestamp)
  {
    this.timestamp = timestamp;
  }
  
  public Integer getAuditRecordId()
  {
    return this.auditRecordId;
  }
  
  public void setAuditRecordId(Integer auditRecordId)
  {
    this.auditRecordId = auditRecordId;
  }
  
  public void copy(EmailLog that)
  {
    setId(that.getId());
    setCounter(that.getCounter());
    setSubject(that.getSubject());
    setContent(that.getContent());
    setCreator(that.getCreator());
    setTimestamp(that.getTimestamp());
  }
  
  public String toString()
  {
    return 
    
      MoreObjects.toStringHelper(this).add("id", this.id).add("counter", this.counter).add("subject", this.subject).add("content", this.content).add("creator", this.creator).add("timestamp", this.timestamp).toString();
  }
}
