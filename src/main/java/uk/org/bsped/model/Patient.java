package uk.org.bsped.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;
import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@Entity
@NamedQueries({@javax.persistence.NamedQuery(name="findAllPatients", query="select myPatient from Patient myPatient"), @javax.persistence.NamedQuery(name="findPatientByDiagnosticCategory", query="select myPatient from Patient myPatient where myPatient.diagnosticCategory = ?1"), @javax.persistence.NamedQuery(name="findPatientByDiagnosticCategoryContaining", query="select myPatient from Patient myPatient where myPatient.diagnosticCategory like ?1"), @javax.persistence.NamedQuery(name="findPatientByDose", query="select myPatient from Patient myPatient where myPatient.dose = ?1"), @javax.persistence.NamedQuery(name="findPatientByFreeText", query="select myPatient from Patient myPatient where myPatient.freeText = ?1"), @javax.persistence.NamedQuery(name="findPatientByInjectionsPerWeek", query="select myPatient from Patient myPatient where myPatient.injectionsPerWeek = ?1"), @javax.persistence.NamedQuery(name="findPatientByInjectionsPerWeekContaining", query="select myPatient from Patient myPatient where myPatient.injectionsPerWeek like ?1"), @javax.persistence.NamedQuery(name="findPatientByBirthMonth", query="select myPatient from Patient myPatient where myPatient.birthMonth = ?1"), @javax.persistence.NamedQuery(name="findPatientByOngoingPrescription", query="select myPatient from Patient myPatient where myPatient.ongoingPrescription = ?1"), @javax.persistence.NamedQuery(name="findPatientByPatientId", query="select myPatient from Patient myPatient where myPatient.patientId = ?1"), @javax.persistence.NamedQuery(name="findPatientByPostcode", query="select myPatient from Patient myPatient where myPatient.postcode = ?1"), @javax.persistence.NamedQuery(name="findPatientByPostcodeContaining", query="select myPatient from Patient myPatient where myPatient.postcode like ?1"), @javax.persistence.NamedQuery(name="findPatientByPrimaryKey", query="select myPatient from Patient myPatient where myPatient.patientId = ?1"), @javax.persistence.NamedQuery(name="findPatientByRecordDate", query="select myPatient from Patient myPatient where myPatient.recordDate = ?1"), @javax.persistence.NamedQuery(name="findPatientByRecordDateAfter", query="select myPatient from Patient myPatient where myPatient.recordDate > ?1"), @javax.persistence.NamedQuery(name="findPatientByRecordDateBefore", query="select myPatient from Patient myPatient where myPatient.recordDate < ?1"), @javax.persistence.NamedQuery(name="findPatientBySex", query="select myPatient from Patient myPatient where myPatient.sex = ?1"), @javax.persistence.NamedQuery(name="findPatientByBirthYear", query="select myPatient from Patient myPatient where myPatient.birthYear = ?1")})
@Table(catalog="ghaudit", name="patient")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace="ghaudit/uk/org/bsped/model", name="Patient")
public class Patient
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  @Column(name="patient_id", nullable=false)
  @Basic(fetch=FetchType.EAGER)
  @Id
  @GeneratedValue
  @XmlElement
  Integer patientId;
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name="record_date")
  @Basic(fetch=FetchType.EAGER)
  @XmlElement
  Calendar recordDate;
  @Column(name="postcode", length=45)
  @Basic(fetch=FetchType.EAGER)
  @XmlElement
  String postcode;
  @Column(name="diagnostic_category", length=200)
  @Basic(fetch=FetchType.EAGER)
  @XmlElement
  String diagnosticCategory;
  @Column(name="sex", length=3)
  @Basic(fetch=FetchType.EAGER)
  @XmlElement
  String sex;
  @Column(name="birth_month")
  @Basic(fetch=FetchType.EAGER)
  @XmlElement
  Integer birthMonth;
  @Column(name="birth_year")
  @Basic(fetch=FetchType.EAGER)
  @XmlElement
  Integer birthYear;
  @Column(name="gh_start_year")
  @Basic(fetch=FetchType.EAGER)
  @XmlElement
  Integer ghStartYear;
  @Column(name="gh_start_month")
  @Basic(fetch=FetchType.EAGER)
  @XmlElement
  Integer ghStartMonth;
  @Column(name="dose", precision=22)
  @Basic(fetch=FetchType.EAGER)
  @XmlElement
  BigDecimal dose;
  @Column(name="weight", precision=22)
  @Basic(fetch=FetchType.EAGER)
  @XmlElement
  BigDecimal weight;
  @Column(name="age", precision=22)
  @Basic(fetch=FetchType.EAGER)
  @XmlElement
  BigDecimal age;
  @Column(name="injections_per_week")
  @Basic(fetch=FetchType.EAGER)
  @XmlElement
  Integer injectionsPerWeek;
  @Column(name="ongoing_prescription", length=100)
  @Basic(fetch=FetchType.EAGER)
  @Lob
  @XmlElement
  String ongoingPrescription;
  @Column(name="gh_preparation", length=500)
  @Basic(fetch=FetchType.EAGER)
  @Lob
  @XmlElement
  String ghPreparation;
  @Column(name="free_text", columnDefinition="TEXT")
  @Basic(fetch=FetchType.EAGER)
  @Lob
  @XmlElement
  String freeText;
  @ManyToOne(fetch=FetchType.EAGER)
  @JoinColumns({@javax.persistence.JoinColumn(name="audit_record_id", referencedColumnName="audit_record_id")})
  @XmlTransient
  AuditRecord auditRecord;
  
  public Integer getPatientId()
  {
    return this.patientId;
  }
  
  public void setPatientId(Integer patientId)
  {
    this.patientId = patientId;
  }
  
  public Calendar getRecordDate()
  {
    return this.recordDate;
  }
  
  public void setRecordDate(Calendar recordDate)
  {
    this.recordDate = recordDate;
  }
  
  public String getPostcode()
  {
    return this.postcode;
  }
  
  public void setPostcode(String postcode)
  {
    this.postcode = postcode;
  }
  
  public String getDiagnosticCategory()
  {
    return this.diagnosticCategory;
  }
  
  public void setDiagnosticCategory(String diagnosticCategory)
  {
    this.diagnosticCategory = diagnosticCategory;
  }
  
  public String getSex()
  {
    return this.sex;
  }
  
  public void setSex(String sex)
  {
    this.sex = sex;
  }
  
  public Integer getBirthYear()
  {
    return this.birthYear;
  }
  
  public void setBirthYear(Integer birthYear)
  {
    this.birthYear = birthYear;
  }
  
  public Integer getBirthMonth()
  {
    return this.birthMonth;
  }
  
  public void setBirthMonth(Integer birthMonth)
  {
    this.birthMonth = birthMonth;
  }
  
  public Integer getGhStartMonth()
  {
    return this.ghStartMonth;
  }
  
  public void setGhStartMonth(Integer ghStartMonth)
  {
    this.ghStartMonth = ghStartMonth;
  }
  
  public Integer getGhStartYear()
  {
    return this.ghStartYear;
  }
  
  public void setGhStartYear(Integer ghStartYear)
  {
    this.ghStartYear = ghStartYear;
  }
  
  public BigDecimal getDose()
  {
    return this.dose;
  }
  
  public void setDose(BigDecimal dose)
  {
    this.dose = dose;
  }
  
  public BigDecimal getWeight()
  {
    return this.weight;
  }
  
  public void setWeight(BigDecimal weight)
  {
    this.weight = weight;
  }
  
  public BigDecimal getAge()
  {
    return this.age;
  }
  
  public void setAge(BigDecimal age)
  {
    this.age = age;
  }
  
  public Integer getInjectionsPerWeek()
  {
    return this.injectionsPerWeek;
  }
  
  public void setInjectionsPerWeek(Integer injectionsPerWeek)
  {
    this.injectionsPerWeek = injectionsPerWeek;
  }
  
  public String getGhPreparation()
  {
    return this.ghPreparation;
  }
  
  public void setGhPreparation(String ghPreparation)
  {
    this.ghPreparation = ghPreparation;
  }
  
  public String getOngoingPrescription()
  {
    return this.ongoingPrescription;
  }
  
  public void setOngoingPrescription(String ongoingPrescription)
  {
    this.ongoingPrescription = ongoingPrescription;
  }
  
  public String getFreeText()
  {
    return this.freeText;
  }
  
  public void setFreeText(String freeText)
  {
    this.freeText = freeText;
  }
  
  public AuditRecord getAuditRecord()
  {
    return this.auditRecord;
  }
  
  public void setAuditRecord(AuditRecord auditRecord)
  {
    this.auditRecord = auditRecord;
  }
  
  public void copy(Patient that)
  {
    setPatientId(that.getPatientId());
    setRecordDate(that.getRecordDate());
    setPostcode(that.getPostcode());
    setDiagnosticCategory(that.getDiagnosticCategory());
    setSex(that.getSex());
    setBirthYear(that.getBirthYear());
    setBirthMonth(that.getBirthMonth());
    setGhStartMonth(that.getGhStartMonth());
    setGhStartYear(that.getGhStartYear());
    setDose(that.getDose());
    setWeight(that.getWeight());
    setAge(that.getAge());
    
    setInjectionsPerWeek(that.getInjectionsPerWeek());
    setGhPreparation(that.getGhPreparation());
    setOngoingPrescription(that.getOngoingPrescription());
    setFreeText(that.getFreeText());
    setAuditRecord(that.getAuditRecord());
  }
  
  public String toString()
  {
    return 
    
      MoreObjects.toStringHelper(this).add("patientId", this.patientId).add("diagnosticCategory", this.diagnosticCategory).add("dose", this.dose).add("weight", this.weight).add("age", this.age).add("injectionsPerWeek", this.injectionsPerWeek).add("birthMonth", this.birthMonth).add("birthYear", this.birthYear).add("ghStartYear", this.ghStartYear).add("ghStartMonth", this.ghStartMonth).add("ongoingPrescription", this.ongoingPrescription).add("ghPreparation", this.ghPreparation).add("postcode", this.postcode).add("recordDate", this.recordDate).add("sex", this.sex).add("freeText", this.freeText).toString();
  }
}