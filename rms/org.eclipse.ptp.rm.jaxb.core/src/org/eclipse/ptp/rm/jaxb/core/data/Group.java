//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0.5-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.02.24 at 04:22:23 PM CST 
//


package org.eclipse.ptp.rm.jaxb.core.data;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
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
 *       &lt;sequence>
 *         &lt;element ref="{}grid-data" minOccurs="0"/>
 *         &lt;element ref="{}grid-layout" minOccurs="0"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{}style" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;choice maxOccurs="unbounded">
 *             &lt;element ref="{}group"/>
 *             &lt;element ref="{}tab-folder"/>
 *             &lt;element ref="{}widget"/>
 *             &lt;element ref="{}viewer"/>
 *           &lt;/choice>
 *           &lt;element ref="{}discovered-attributes"/>
 *           &lt;element ref="{}all-attributes"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="dynamic" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="scrollable" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "gridData",
    "gridLayout",
    "title",
    "style",
    "groupOrTabFolderOrWidget",
    "discoveredAttributes",
    "allAttributes"
})
@XmlRootElement(name = "group")
public class Group {

    @XmlElement(name = "grid-data")
    protected GridData gridData;
    @XmlElement(name = "grid-layout")
    protected GridLayout gridLayout;
    protected String title;
    protected Style style;
    @XmlElements({
        @XmlElement(name = "group", type = Group.class),
        @XmlElement(name = "tab-folder", type = TabFolder.class),
        @XmlElement(name = "viewer", type = Viewer.class),
        @XmlElement(name = "widget", type = Widget.class)
    })
    protected List<Object> groupOrTabFolderOrWidget;
    @XmlElement(name = "discovered-attributes")
    protected DiscoveredAttributes discoveredAttributes;
    @XmlElement(name = "all-attributes")
    protected AllAttributes allAttributes;
    @XmlAttribute
    protected Boolean dynamic;
    @XmlAttribute
    protected Boolean scrollable;

    /**
     * Gets the value of the gridData property.
     * 
     * @return
     *     possible object is
     *     {@link GridData }
     *     
     */
    public GridData getGridData() {
        return gridData;
    }

    /**
     * Sets the value of the gridData property.
     * 
     * @param value
     *     allowed object is
     *     {@link GridData }
     *     
     */
    public void setGridData(GridData value) {
        this.gridData = value;
    }

    /**
     * Gets the value of the gridLayout property.
     * 
     * @return
     *     possible object is
     *     {@link GridLayout }
     *     
     */
    public GridLayout getGridLayout() {
        return gridLayout;
    }

    /**
     * Sets the value of the gridLayout property.
     * 
     * @param value
     *     allowed object is
     *     {@link GridLayout }
     *     
     */
    public void setGridLayout(GridLayout value) {
        this.gridLayout = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the style property.
     * 
     * @return
     *     possible object is
     *     {@link Style }
     *     
     */
    public Style getStyle() {
        return style;
    }

    /**
     * Sets the value of the style property.
     * 
     * @param value
     *     allowed object is
     *     {@link Style }
     *     
     */
    public void setStyle(Style value) {
        this.style = value;
    }

    /**
     * Gets the value of the groupOrTabFolderOrWidget property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the groupOrTabFolderOrWidget property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGroupOrTabFolderOrWidget().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Group }
     * {@link TabFolder }
     * {@link Viewer }
     * {@link Widget }
     * 
     * 
     */
    public List<Object> getGroupOrTabFolderOrWidget() {
        if (groupOrTabFolderOrWidget == null) {
            groupOrTabFolderOrWidget = new ArrayList<Object>();
        }
        return this.groupOrTabFolderOrWidget;
    }

    /**
     * Gets the value of the discoveredAttributes property.
     * 
     * @return
     *     possible object is
     *     {@link DiscoveredAttributes }
     *     
     */
    public DiscoveredAttributes getDiscoveredAttributes() {
        return discoveredAttributes;
    }

    /**
     * Sets the value of the discoveredAttributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link DiscoveredAttributes }
     *     
     */
    public void setDiscoveredAttributes(DiscoveredAttributes value) {
        this.discoveredAttributes = value;
    }

    /**
     * Gets the value of the allAttributes property.
     * 
     * @return
     *     possible object is
     *     {@link AllAttributes }
     *     
     */
    public AllAttributes getAllAttributes() {
        return allAttributes;
    }

    /**
     * Sets the value of the allAttributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link AllAttributes }
     *     
     */
    public void setAllAttributes(AllAttributes value) {
        this.allAttributes = value;
    }

    /**
     * Gets the value of the dynamic property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDynamic() {
        return dynamic;
    }

    /**
     * Sets the value of the dynamic property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDynamic(Boolean value) {
        this.dynamic = value;
    }

    /**
     * Gets the value of the scrollable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isScrollable() {
        return scrollable;
    }

    /**
     * Sets the value of the scrollable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setScrollable(Boolean value) {
        this.scrollable = value;
    }

}
