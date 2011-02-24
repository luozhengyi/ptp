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
 *         &lt;element ref="{}property" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}managed-files" minOccurs="0"/>
 *         &lt;element ref="{}on-start-up" minOccurs="0"/>
 *         &lt;element ref="{}discover-attributes" minOccurs="0"/>
 *         &lt;element name="run-commands">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice maxOccurs="3">
 *                   &lt;element ref="{}run-interactive"/>
 *                   &lt;element ref="{}run-batch"/>
 *                   &lt;element ref="{}run-debug"/>
 *                 &lt;/choice>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element ref="{}cancel-job"/>
 *         &lt;element ref="{}suspend-job" minOccurs="0"/>
 *         &lt;element ref="{}resume-job" minOccurs="0"/>
 *         &lt;element ref="{}on-shut-down" minOccurs="0"/>
 *         &lt;element ref="{}commands" minOccurs="0"/>
 *         &lt;element ref="{}parsers" minOccurs="0"/>
 *         &lt;element ref="{}script" minOccurs="0"/>
 *         &lt;element ref="{}attribute-definitions"/>
 *         &lt;element ref="{}launch-tab"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "property",
    "managedFiles",
    "onStartUp",
    "discoverAttributes",
    "runCommands",
    "cancelJob",
    "suspendJob",
    "resumeJob",
    "onShutDown",
    "commands",
    "parsers",
    "script",
    "attributeDefinitions",
    "launchTab"
})
@XmlRootElement(name = "control")
public class Control {

    protected List<Property> property;
    @XmlElement(name = "managed-files")
    protected ManagedFiles managedFiles;
    @XmlElement(name = "on-start-up")
    protected OnStartUp onStartUp;
    @XmlElement(name = "discover-attributes")
    protected DiscoverAttributes discoverAttributes;
    @XmlElement(name = "run-commands", required = true)
    protected Control.RunCommands runCommands;
    @XmlElement(name = "cancel-job", required = true)
    protected CancelJob cancelJob;
    @XmlElement(name = "suspend-job")
    protected SuspendJob suspendJob;
    @XmlElement(name = "resume-job")
    protected ResumeJob resumeJob;
    @XmlElement(name = "on-shut-down")
    protected OnShutDown onShutDown;
    protected Commands commands;
    protected Parsers parsers;
    protected Script script;
    @XmlElement(name = "attribute-definitions", required = true)
    protected AttributeDefinitions attributeDefinitions;
    @XmlElement(name = "launch-tab", required = true)
    protected LaunchTab launchTab;

    /**
     * Gets the value of the property property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the property property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProperty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Property }
     * 
     * 
     */
    public List<Property> getProperty() {
        if (property == null) {
            property = new ArrayList<Property>();
        }
        return this.property;
    }

    /**
     * Gets the value of the managedFiles property.
     * 
     * @return
     *     possible object is
     *     {@link ManagedFiles }
     *     
     */
    public ManagedFiles getManagedFiles() {
        return managedFiles;
    }

    /**
     * Sets the value of the managedFiles property.
     * 
     * @param value
     *     allowed object is
     *     {@link ManagedFiles }
     *     
     */
    public void setManagedFiles(ManagedFiles value) {
        this.managedFiles = value;
    }

    /**
     * Gets the value of the onStartUp property.
     * 
     * @return
     *     possible object is
     *     {@link OnStartUp }
     *     
     */
    public OnStartUp getOnStartUp() {
        return onStartUp;
    }

    /**
     * Sets the value of the onStartUp property.
     * 
     * @param value
     *     allowed object is
     *     {@link OnStartUp }
     *     
     */
    public void setOnStartUp(OnStartUp value) {
        this.onStartUp = value;
    }

    /**
     * Gets the value of the discoverAttributes property.
     * 
     * @return
     *     possible object is
     *     {@link DiscoverAttributes }
     *     
     */
    public DiscoverAttributes getDiscoverAttributes() {
        return discoverAttributes;
    }

    /**
     * Sets the value of the discoverAttributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link DiscoverAttributes }
     *     
     */
    public void setDiscoverAttributes(DiscoverAttributes value) {
        this.discoverAttributes = value;
    }

    /**
     * Gets the value of the runCommands property.
     * 
     * @return
     *     possible object is
     *     {@link Control.RunCommands }
     *     
     */
    public Control.RunCommands getRunCommands() {
        return runCommands;
    }

    /**
     * Sets the value of the runCommands property.
     * 
     * @param value
     *     allowed object is
     *     {@link Control.RunCommands }
     *     
     */
    public void setRunCommands(Control.RunCommands value) {
        this.runCommands = value;
    }

    /**
     * Gets the value of the cancelJob property.
     * 
     * @return
     *     possible object is
     *     {@link CancelJob }
     *     
     */
    public CancelJob getCancelJob() {
        return cancelJob;
    }

    /**
     * Sets the value of the cancelJob property.
     * 
     * @param value
     *     allowed object is
     *     {@link CancelJob }
     *     
     */
    public void setCancelJob(CancelJob value) {
        this.cancelJob = value;
    }

    /**
     * Gets the value of the suspendJob property.
     * 
     * @return
     *     possible object is
     *     {@link SuspendJob }
     *     
     */
    public SuspendJob getSuspendJob() {
        return suspendJob;
    }

    /**
     * Sets the value of the suspendJob property.
     * 
     * @param value
     *     allowed object is
     *     {@link SuspendJob }
     *     
     */
    public void setSuspendJob(SuspendJob value) {
        this.suspendJob = value;
    }

    /**
     * Gets the value of the resumeJob property.
     * 
     * @return
     *     possible object is
     *     {@link ResumeJob }
     *     
     */
    public ResumeJob getResumeJob() {
        return resumeJob;
    }

    /**
     * Sets the value of the resumeJob property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResumeJob }
     *     
     */
    public void setResumeJob(ResumeJob value) {
        this.resumeJob = value;
    }

    /**
     * Gets the value of the onShutDown property.
     * 
     * @return
     *     possible object is
     *     {@link OnShutDown }
     *     
     */
    public OnShutDown getOnShutDown() {
        return onShutDown;
    }

    /**
     * Sets the value of the onShutDown property.
     * 
     * @param value
     *     allowed object is
     *     {@link OnShutDown }
     *     
     */
    public void setOnShutDown(OnShutDown value) {
        this.onShutDown = value;
    }

    /**
     * Gets the value of the commands property.
     * 
     * @return
     *     possible object is
     *     {@link Commands }
     *     
     */
    public Commands getCommands() {
        return commands;
    }

    /**
     * Sets the value of the commands property.
     * 
     * @param value
     *     allowed object is
     *     {@link Commands }
     *     
     */
    public void setCommands(Commands value) {
        this.commands = value;
    }

    /**
     * Gets the value of the parsers property.
     * 
     * @return
     *     possible object is
     *     {@link Parsers }
     *     
     */
    public Parsers getParsers() {
        return parsers;
    }

    /**
     * Sets the value of the parsers property.
     * 
     * @param value
     *     allowed object is
     *     {@link Parsers }
     *     
     */
    public void setParsers(Parsers value) {
        this.parsers = value;
    }

    /**
     * Gets the value of the script property.
     * 
     * @return
     *     possible object is
     *     {@link Script }
     *     
     */
    public Script getScript() {
        return script;
    }

    /**
     * Sets the value of the script property.
     * 
     * @param value
     *     allowed object is
     *     {@link Script }
     *     
     */
    public void setScript(Script value) {
        this.script = value;
    }

    /**
     * Gets the value of the attributeDefinitions property.
     * 
     * @return
     *     possible object is
     *     {@link AttributeDefinitions }
     *     
     */
    public AttributeDefinitions getAttributeDefinitions() {
        return attributeDefinitions;
    }

    /**
     * Sets the value of the attributeDefinitions property.
     * 
     * @param value
     *     allowed object is
     *     {@link AttributeDefinitions }
     *     
     */
    public void setAttributeDefinitions(AttributeDefinitions value) {
        this.attributeDefinitions = value;
    }

    /**
     * Gets the value of the launchTab property.
     * 
     * @return
     *     possible object is
     *     {@link LaunchTab }
     *     
     */
    public LaunchTab getLaunchTab() {
        return launchTab;
    }

    /**
     * Sets the value of the launchTab property.
     * 
     * @param value
     *     allowed object is
     *     {@link LaunchTab }
     *     
     */
    public void setLaunchTab(LaunchTab value) {
        this.launchTab = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;choice maxOccurs="3">
     *         &lt;element ref="{}run-interactive"/>
     *         &lt;element ref="{}run-batch"/>
     *         &lt;element ref="{}run-debug"/>
     *       &lt;/choice>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "runInteractiveOrRunBatchOrRunDebug"
    })
    public static class RunCommands {

        @XmlElements({
            @XmlElement(name = "run-interactive", type = RunInteractive.class),
            @XmlElement(name = "run-batch", type = RunBatch.class),
            @XmlElement(name = "run-debug", type = RunDebug.class)
        })
        protected List<Object> runInteractiveOrRunBatchOrRunDebug;

        /**
         * Gets the value of the runInteractiveOrRunBatchOrRunDebug property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the runInteractiveOrRunBatchOrRunDebug property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRunInteractiveOrRunBatchOrRunDebug().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link RunInteractive }
         * {@link RunBatch }
         * {@link RunDebug }
         * 
         * 
         */
        public List<Object> getRunInteractiveOrRunBatchOrRunDebug() {
            if (runInteractiveOrRunBatchOrRunDebug == null) {
                runInteractiveOrRunBatchOrRunDebug = new ArrayList<Object>();
            }
            return this.runInteractiveOrRunBatchOrRunDebug;
        }

    }

}
