//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0.5-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.02.24 at 04:22:23 PM CST 
//


package org.eclipse.ptp.rm.jaxb.core.data;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="grabExcessHorizontal" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="grabExcessVertical" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="heightHint" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="horizontalAlign" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="horizontalSpan" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="minHeight" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="minWidth" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="verticalAlign" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="verticalSpan" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="widthHint" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "grid-data")
public class GridData {

    @XmlAttribute
    protected Boolean grabExcessHorizontal;
    @XmlAttribute
    protected Boolean grabExcessVertical;
    @XmlAttribute
    protected BigInteger heightHint;
    @XmlAttribute
    protected String horizontalAlign;
    @XmlAttribute
    protected BigInteger horizontalSpan;
    @XmlAttribute
    protected BigInteger minHeight;
    @XmlAttribute
    protected BigInteger minWidth;
    @XmlAttribute
    protected String verticalAlign;
    @XmlAttribute
    protected BigInteger verticalSpan;
    @XmlAttribute
    protected BigInteger widthHint;

    /**
     * Gets the value of the grabExcessHorizontal property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isGrabExcessHorizontal() {
        return grabExcessHorizontal;
    }

    /**
     * Sets the value of the grabExcessHorizontal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setGrabExcessHorizontal(Boolean value) {
        this.grabExcessHorizontal = value;
    }

    /**
     * Gets the value of the grabExcessVertical property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isGrabExcessVertical() {
        return grabExcessVertical;
    }

    /**
     * Sets the value of the grabExcessVertical property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setGrabExcessVertical(Boolean value) {
        this.grabExcessVertical = value;
    }

    /**
     * Gets the value of the heightHint property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getHeightHint() {
        return heightHint;
    }

    /**
     * Sets the value of the heightHint property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setHeightHint(BigInteger value) {
        this.heightHint = value;
    }

    /**
     * Gets the value of the horizontalAlign property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHorizontalAlign() {
        return horizontalAlign;
    }

    /**
     * Sets the value of the horizontalAlign property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHorizontalAlign(String value) {
        this.horizontalAlign = value;
    }

    /**
     * Gets the value of the horizontalSpan property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getHorizontalSpan() {
        return horizontalSpan;
    }

    /**
     * Sets the value of the horizontalSpan property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setHorizontalSpan(BigInteger value) {
        this.horizontalSpan = value;
    }

    /**
     * Gets the value of the minHeight property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMinHeight() {
        return minHeight;
    }

    /**
     * Sets the value of the minHeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMinHeight(BigInteger value) {
        this.minHeight = value;
    }

    /**
     * Gets the value of the minWidth property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMinWidth() {
        return minWidth;
    }

    /**
     * Sets the value of the minWidth property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMinWidth(BigInteger value) {
        this.minWidth = value;
    }

    /**
     * Gets the value of the verticalAlign property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVerticalAlign() {
        return verticalAlign;
    }

    /**
     * Sets the value of the verticalAlign property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVerticalAlign(String value) {
        this.verticalAlign = value;
    }

    /**
     * Gets the value of the verticalSpan property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getVerticalSpan() {
        return verticalSpan;
    }

    /**
     * Sets the value of the verticalSpan property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setVerticalSpan(BigInteger value) {
        this.verticalSpan = value;
    }

    /**
     * Gets the value of the widthHint property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getWidthHint() {
        return widthHint;
    }

    /**
     * Sets the value of the widthHint property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setWidthHint(BigInteger value) {
        this.widthHint = value;
    }

}
