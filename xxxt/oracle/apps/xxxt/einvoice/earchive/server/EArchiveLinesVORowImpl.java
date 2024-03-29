package xxxt.oracle.apps.xxxt.einvoice.earchive.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class EArchiveLinesVORowImpl extends OAViewRowImpl {
    public static final int EINVINVOICEID = 0;
    public static final int EINVLINEID = 1;
    public static final int LINEID = 2;
    public static final int INVOICEDQUANTITY = 3;
    public static final int INVOICEDUOM = 4;
    public static final int PRICE = 5;
    public static final int PRICECURRENCY = 6;
    public static final int ITEMNAME = 7;
    public static final int EARCHIVEHEADERVO = 8;

    /**This is the default constructor (do not remove)
     */
    public EArchiveLinesVORowImpl() {
    }

    /**Gets the attribute value for the calculated attribute EinvInvoiceId
     */
    public Number getEinvInvoiceId() {
        return (Number) getAttributeInternal(EINVINVOICEID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute EinvInvoiceId
     */
    public void setEinvInvoiceId(Number value) {
        setAttributeInternal(EINVINVOICEID, value);
    }

    /**Gets the attribute value for the calculated attribute EinvLineId
     */
    public Number getEinvLineId() {
        return (Number) getAttributeInternal(EINVLINEID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute EinvLineId
     */
    public void setEinvLineId(Number value) {
        setAttributeInternal(EINVLINEID, value);
    }

    /**Gets the attribute value for the calculated attribute LineId
     */
    public Number getLineId() {
        return (Number) getAttributeInternal(LINEID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute LineId
     */
    public void setLineId(Number value) {
        setAttributeInternal(LINEID, value);
    }

    /**Gets the attribute value for the calculated attribute InvoicedQuantity
     */
    public Number getInvoicedQuantity() {
        return (Number) getAttributeInternal(INVOICEDQUANTITY);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute InvoicedQuantity
     */
    public void setInvoicedQuantity(Number value) {
        setAttributeInternal(INVOICEDQUANTITY, value);
    }

    /**Gets the attribute value for the calculated attribute InvoicedUom
     */
    public String getInvoicedUom() {
        return (String) getAttributeInternal(INVOICEDUOM);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute InvoicedUom
     */
    public void setInvoicedUom(String value) {
        setAttributeInternal(INVOICEDUOM, value);
    }

    /**Gets the attribute value for the calculated attribute Price
     */
    public Number getPrice() {
        return (Number) getAttributeInternal(PRICE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Price
     */
    public void setPrice(Number value) {
        setAttributeInternal(PRICE, value);
    }

    /**Gets the attribute value for the calculated attribute PriceCurrency
     */
    public String getPriceCurrency() {
        return (String) getAttributeInternal(PRICECURRENCY);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute PriceCurrency
     */
    public void setPriceCurrency(String value) {
        setAttributeInternal(PRICECURRENCY, value);
    }

    /**Gets the attribute value for the calculated attribute ItemName
     */
    public String getItemName() {
        return (String) getAttributeInternal(ITEMNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ItemName
     */
    public void setItemName(String value) {
        setAttributeInternal(ITEMNAME, value);
    }

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case EINVINVOICEID:
            return getEinvInvoiceId();
        case EINVLINEID:
            return getEinvLineId();
        case LINEID:
            return getLineId();
        case INVOICEDQUANTITY:
            return getInvoicedQuantity();
        case INVOICEDUOM:
            return getInvoicedUom();
        case PRICE:
            return getPrice();
        case PRICECURRENCY:
            return getPriceCurrency();
        case ITEMNAME:
            return getItemName();
        case EARCHIVEHEADERVO:
            return getEArchiveHeaderVO();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case EINVINVOICEID:
            setEinvInvoiceId((Number)value);
            return;
        case EINVLINEID:
            setEinvLineId((Number)value);
            return;
        case LINEID:
            setLineId((Number)value);
            return;
        case INVOICEDQUANTITY:
            setInvoicedQuantity((Number)value);
            return;
        case INVOICEDUOM:
            setInvoicedUom((String)value);
            return;
        case PRICE:
            setPrice((Number)value);
            return;
        case PRICECURRENCY:
            setPriceCurrency((String)value);
            return;
        case ITEMNAME:
            setItemName((String)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }

    /**Gets the associated <code>Row</code> using master-detail link EArchiveHeaderVO
     */
    public Row getEArchiveHeaderVO() {
        return (Row)getAttributeInternal(EARCHIVEHEADERVO);
    }
}
