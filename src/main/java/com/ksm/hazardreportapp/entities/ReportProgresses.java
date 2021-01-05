/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.hazardreportapp.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kelvi
 */
@Entity
@Table(name = "report_progresses", catalog = "hazardreportdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReportProgresses.findAll", query = "SELECT r FROM ReportProgresses r")
    , @NamedQuery(name = "ReportProgresses.findById", query = "SELECT r FROM ReportProgresses r WHERE r.id = :id")
    , @NamedQuery(name = "ReportProgresses.findByDate", query = "SELECT r FROM ReportProgresses r WHERE r.date = :date")})
public class ReportProgresses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @JoinColumn(name = "report", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Reports report;
    @JoinColumn(name = "status", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Statuses status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reportProgress", fetch = FetchType.LAZY)
    private List<Actions> actionsList;

    public ReportProgresses() {
    }

    public ReportProgresses(Integer id) {
        this.id = id;
    }

    public ReportProgresses(Integer id, Date date) {
        this.id = id;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Reports getReport() {
        return report;
    }

    public void setReport(Reports report) {
        this.report = report;
    }

    public Statuses getStatus() {
        return status;
    }

    public void setStatus(Statuses status) {
        this.status = status;
    }

    @XmlTransient
    public List<Actions> getActionsList() {
        return actionsList;
    }

    public void setActionsList(List<Actions> actionsList) {
        this.actionsList = actionsList;
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
        if (!(object instanceof ReportProgresses)) {
            return false;
        }
        ReportProgresses other = (ReportProgresses) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ksm.hazardreportapp.entities.ReportProgresses[ id=" + id + " ]";
    }

}
