/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.hazardreportapp.entities;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kelvi
 */
@Entity
@Table(name = "image_attachments", catalog = "hazardreportdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ImageAttachments.findAll", query = "SELECT i FROM ImageAttachments i")
    , @NamedQuery(name = "ImageAttachments.findById", query = "SELECT i FROM ImageAttachments i WHERE i.id = :id")
    , @NamedQuery(name = "ImageAttachments.findByImagePath", query = "SELECT i FROM ImageAttachments i WHERE i.imagePath = :imagePath")})
public class ImageAttachments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "image_path")
    private String imagePath;
    @JoinColumn(name = "report", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Reports report;

    public ImageAttachments() {
    }

    public ImageAttachments(Integer id) {
        this.id = id;
    }

    public ImageAttachments(Integer id, String imagePath) {
        this.id = id;
        this.imagePath = imagePath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Reports getReport() {
        return report;
    }

    public void setReport(Reports report) {
        this.report = report;
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
        if (!(object instanceof ImageAttachments)) {
            return false;
        }
        ImageAttachments other = (ImageAttachments) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ksm.hazardreportapp.entities.ImageAttachments[ id=" + id + " ]";
    }

}
