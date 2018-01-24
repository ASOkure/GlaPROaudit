package uk.org.bsped.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "findAllAuthoritiess", query = "select myAuthorities from Authorities myAuthorities"),
        @NamedQuery(name = "findAuthoritiesByAuthorityname", query = "select myAuthorities from Authorities myAuthorities where myAuthorities.authorityname = ?1"),
        @NamedQuery(name = "findAuthoritiesByAuthoritynameContaining", query = "select myAuthorities from Authorities myAuthorities where myAuthorities.authorityname like ?1"),
        @NamedQuery(name = "findAuthoritiesById", query = "select myAuthorities from Authorities myAuthorities where myAuthorities.id = ?1"),
        @NamedQuery(name = "findAuthoritiesByPrimaryKey", query = "select myAuthorities from Authorities myAuthorities where myAuthorities.id = ?1")})
@Table(catalog = "ghaudit", name = "authorities")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "ghaudit/uk/org/bsped/model", name = "Authorities")
public class Authorities implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "authorityname")
    @Basic(fetch = FetchType.EAGER)
    @XmlElement
    String authorityname;

    @Column(name = "id", nullable = false)
    @Basic(fetch = FetchType.EAGER)
    @Id
    @GeneratedValue
    @XmlElement
    Integer id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(catalog = "ghaudit", name = "users_authorities", joinColumns = {@JoinColumn(name = "authorities_id", referencedColumnName = "id", nullable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, updatable = false)})
    @XmlElement(name = "", namespace = "")
    Set<Users> userses;

    public Authorities() {
    }

    public void copy(Authorities that) {
        setId(that.getId());
        setAuthorityname(that.getAuthorityname());
        setUserses(new LinkedHashSet<Users>(that.getUserses()));
    }

    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Authorities))
            return false;
        Authorities equalCheck = (Authorities) obj;
        if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
            return false;
        if (id != null && !id.equals(equalCheck.id))
            return false;
        return true;
    }

    public String getAuthorityname() {
        return this.authorityname;
    }

    public Integer getId() {
        return this.id;
    }

    public Set<Users> getUserses() {
        if (userses == null) {
            userses = new LinkedHashSet<Users>();
        }
        return userses;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (int) (prime * result + ((id == null) ? 0 : id.hashCode()));
        return result;
    }

    public void setAuthorityname(String authorityname) {
        this.authorityname = authorityname;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserses(Set<Users> userses) {
        this.userses = userses;
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("id=[").append(id).append("] ");
        buffer.append("authorityname=[").append(authorityname).append("] ");
        return buffer.toString();
    }
}
