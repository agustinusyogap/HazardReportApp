/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.hazardreportapp.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kelvi
 */
@Entity
@Table(name = "statuses", catalog = "hazardreportdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Statuses.findAll", query = "SELECT s FROM Statuses s")
    , @NamedQuery(name = "Statuses.findById", query = "SELECT s FROM Statuses s WHERE s.id = :id")
    , @NamedQuery(name = "Statuses.findByName", query = "SELECT s FROM Statuses s WHERE s.name = :name")
    , @NamedQuery(name = "Statuses.findByMessage", query = "SELECT s FROM Statuses s WHERE s.message = :message")})
public class Statuses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "message")
    private String message;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "currentStatus", fetch = FetchType.LAZY)
    private List<Reports> reportsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "status", fetch = FetchType.LAZY)
    private List<ReportProgresses> reportProgressesList;

    public Statuses() {
    }

    public Statuses(Integer id) {
        this.id = id;
    }

    public Statuses(Integer id, String name, String message) {
        this.id = id;
        this.name = name;
        this.message = message;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @XmlTransient
    public List<Reports> getReportsList() {
        return reportsList;
    }

    public void setReportsList(List<Reports> reportsList) {
        this.reportsList = reportsList;
    }

    @XmlTransient
    public List<ReportProgresses> getReportProgressesList() {
        return reportProgressesList;
    }

    public void setReportProgressesList(List<ReportProgresses> reportProgressesList) {
        this.reportProgressesList = reportProgressesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Statuses)) {
            return false;
        }
        Statuses other = (Statuses) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ksm.hazardreportapp.entities.Statuses[ id=" + id + " ]";
    }

}
