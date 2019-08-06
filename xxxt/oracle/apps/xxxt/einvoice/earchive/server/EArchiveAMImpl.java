package xxxt.oracle.apps.xxxt.einvoice.earchive.server;

import java.io.IOException;
import java.io.InputStream;

import java.sql.Blob;
import java.sql.Types;

import javax.servlet.http.HttpServletResponse;

import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.server.OAApplicationModuleImpl;

import oracle.apps.fnd.framework.webui.OAPageContext;

import oracle.jbo.RowSetIterator;
import oracle.jbo.server.ViewLinkImpl;

import oracle.jdbc.OracleCallableStatement;

import xxxt.oracle.apps.xxxt.einvoice.earchive.lov.server.EArchiveSourcesVOImpl;
import xxxt.oracle.apps.xxxt.einvoice.earchive.lov.server.EArchiveStatusesVOImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class EArchiveAMImpl extends OAApplicationModuleImpl {
   
   public void initSearchPanel(){
        EArchiveSearchPanelVOImpl vo =getEArchiveSearchPanelVO1();
        vo.setWhereClause("1=2");
        vo.executeQuery();
        vo.insertRow(vo.createRow());
   }
    public void setPolicies(OAPageContext pageContext)  {
            OracleCallableStatement callableStatement = null;
            int orgId =-1;
            try {
                String callProc = 
                    " BEGIN XXXT_EARCHIVE_SCREEN_PKG.set_policies(:1); END; ";
                try {
                    orgId = Integer.parseInt(pageContext.getProfile("ORG_ID")); 
                } catch (Exception e) {
                    e.printStackTrace();
                }
                callableStatement = 
                        (OracleCallableStatement)getOADBTransaction().createCallableStatement(callProc, 
                                                                                              1);
                callableStatement.setInt(1,orgId);
                callableStatement.execute();
            } catch (Exception e) {
                e.printStackTrace();  
            } finally {
                try {
                    callableStatement.close();
                } catch (Exception exception2) {
                    throw OAException.wrapperException(exception2);
                }
            }
        }
   
   public void initSearch(){
       EArchiveHeaderVOImpl resultVO =getEArchiveHeaderVO1() ;
     //  resultVO.setWhereClause("1=1");
     //  resultVO.executeQuery();   
       
       EArchiveSearchPanelVOImpl panelVo =getEArchiveSearchPanelVO1();
       EArchiveSearchPanelVORowImpl panelRow=(EArchiveSearchPanelVORowImpl)panelVo.first();
       String whereClause ="1=1  " ;
       Object[] params =new Object[20];
       
       resultVO.setWhereClauseParams(null);
       resultVO.setWhereClause(null);
       
       int i=1;
       if(panelRow.getEArsivNo()!=null  && !"".equals(panelRow.getEArsivNo())){
           whereClause=whereClause+" and  invoice_id like :"+i;
           resultVO.setWhereClauseParam(i-1,panelRow.getEArsivNo());
           i++;
       }
       
       if(panelRow.getTcknVkn()!=null  && !"".equals(panelRow.getTcknVkn())){
           whereClause=whereClause+" and  tax_reg_number like :"+i;
           resultVO.setWhereClauseParam(i-1,panelRow.getTcknVkn());
           i++;
       }
       
       if(panelRow.getFaturaTarihiBaslangic()!=null  && !"".equals(panelRow.getFaturaTarihiBaslangic())){
           whereClause=whereClause+" and  invoice_date >= :"+i;
           resultVO.setWhereClauseParam(i-1,panelRow.getFaturaTarihiBaslangic());
           i++;
       }
       
       if(panelRow.getFaturaTarihiBitis()!=null  && !"".equals(panelRow.getFaturaTarihiBitis())){
           whereClause=whereClause+" and  invoice_date <= :"+i;
           resultVO.setWhereClauseParam(i-1,panelRow.getFaturaTarihiBitis());
           i++;
       }
       
       if(panelRow.getFaturaGonderimBaslangic()!=null  && !"".equals(panelRow.getFaturaGonderimBaslangic())){
           whereClause=whereClause+" and  sent_date >= :"+i;
           resultVO.setWhereClauseParam(i-1,panelRow.getFaturaGonderimBaslangic());
           i++;
       }
       
       if(panelRow.getFaturaGonderimBitis()!=null  && !"".equals(panelRow.getFaturaGonderimBitis())){
           whereClause=whereClause+" and  sent_date <= :"+i;
           resultVO.setWhereClauseParam(i-1,panelRow.getFaturaGonderimBitis());
           i++;
       }
       
       if(panelRow.getStatus()!=null  && !"".equals(panelRow.getStatus())){
           whereClause=whereClause+" and  status_id = :"+i;
           resultVO.setWhereClauseParam(i-1,panelRow.getStatus());
           i++;
       }
       
       if(panelRow.getSource()!=null  && !"".equals(panelRow.getSource())){
           whereClause=whereClause+" and  internal_source = :"+i;
           resultVO.setWhereClauseParam(i-1,panelRow.getSource());
           i++;
       }
       
       resultVO.setWhereClause(whereClause);
       resultVO.executeQuery();
       resultVO.setWhereClause(null);
    
   }
   
  
   
    public void clearSearch(){
        EArchiveHeaderVOImpl resultVO =getEArchiveHeaderVO1() ;
        resultVO.setWhereClause("1=2");
        resultVO.executeQuery();   
        initSearchPanel();
        
    
    }
   
   public void sendInvoices(OAPageContext pageContext) throws OAException{
       EArchiveHeaderVOImpl resultVO =getEArchiveHeaderVO1() ;
       RowSetIterator rsi = resultVO.createRowSetIterator("RSI");
       int selectedCount=0 ;
       for(EArchiveHeaderVORowImpl row =(EArchiveHeaderVORowImpl)rsi.first();row!=null;row =(EArchiveHeaderVORowImpl)rsi.next()){
          System.out.println("row.getSelectedFlag():"+row.getSelectedFlag());
          if ("Y".equals(row.getSelectedFlag())){
              selectedCount++;
          }
       }
       rsi.closeRowSetIterator();
       if(selectedCount == 0 ){
            throw new OAException("\u0130\u015Flem i\u00E7in l\u00FCtfen sat\u0131r se\u00E7iniz .",OAException.ERROR) ;
       }
       
       rsi = resultVO.createRowSetIterator("RSI2");
       boolean errorStat =false ;
       for(EArchiveHeaderVORowImpl row =(EArchiveHeaderVORowImpl)rsi.first();row!=null ; row =(EArchiveHeaderVORowImpl)rsi.next()){
           String error  =sendSingleInvoice(row.getEinvInvoiceId().intValue()) ;
           getTransaction().commit();
           if(error!=null){
              pageContext.putDialogMessage(new OAException("Fatura No :"+row.getInvoiceId()+" Hata:"+error,OAException.ERROR));
              errorStat=true;
           }
       }
       rsi.closeRowSetIterator();
       if(!errorStat){
           resultVO.executeQuery();
           throw new OAException("G\u00F6nderim ba\u015Far\u0131yla sonu\u00E7land\u0131.",OAException.INFORMATION) ;
       }
   }
   
   
   public String sendSingleInvoice(Number eInvoiceId){
         OracleCallableStatement callableStatement = null;   
         try   
         {   
              String callProc = " BEGIN XXXT_EARCHIVE_SCREEN_PKG.send_invoice(:1,:2,:3); END; ";   
              callableStatement = (OracleCallableStatement)getOADBTransaction().createCallableStatement(callProc,1);   
              callableStatement.setInt(1, eInvoiceId.intValue());     
              callableStatement.registerOutParameter(2,Types.VARCHAR);   
              callableStatement.registerOutParameter(3,Types.VARCHAR);      
              callableStatement.execute();   
              String status = (String)callableStatement.getString(2);   
              String error  = (String)callableStatement.getString(3);   
              if("ERROR".equals(status)){
                return error ;
              }else{
                return null ;
              }
         }   
         catch(Exception e)   
         {   
              e.printStackTrace();   
              return "Prosedur cagirisinda Hata - XXXT_EARCHIVE_SCREEN_PKG.send_invoice";
         }finally   
         {   
              try   
              {   
                   callableStatement.close();   
              }   
              catch(Exception exception2)   
              {   
                   throw OAException.wrapperException(exception2);   
              }   
         }   
   }
   
    public void cancelInvoices(OAPageContext pageContext) throws OAException{
        EArchiveHeaderVOImpl resultVO =getEArchiveHeaderVO1() ;
        RowSetIterator rsi = resultVO.createRowSetIterator("RSI");
        int selectedCount=0 ;
        for(EArchiveHeaderVORowImpl row =(EArchiveHeaderVORowImpl)rsi.first();row!=null ; row =(EArchiveHeaderVORowImpl)rsi.next()){
           if ("Y".equals(row.getSelectedFlag())){
               selectedCount++;
           }
        }
        rsi.closeRowSetIterator();
        if(selectedCount == 0 ){
             throw new OAException("\u0130\u015Flem i\u00E7in l\u00FCtfen sat\u0131r se\u00E7iniz .",OAException.ERROR) ;
        }
        
        rsi = resultVO.createRowSetIterator("RSI2");
        boolean errorStat =false ;
        for(EArchiveHeaderVORowImpl row =(EArchiveHeaderVORowImpl)rsi.first();row!=null ; row =(EArchiveHeaderVORowImpl)rsi.next()){
            String error  =cancelSingleInvoice(row.getEinvInvoiceId().intValue()) ;
            getTransaction().commit();
            if(error!=null){
               pageContext.putDialogMessage(new OAException("Fatura No :"+row.getInvoiceId()+" Hata:"+error,OAException.ERROR));
               errorStat=true;
            }
        }
        if(!errorStat){
            resultVO.executeQuery();
            throw new OAException("\u0130ptal \u0130\u015Flemi ba\u015Far\u0131yla sonu\u00E7land\u0131.",OAException.INFORMATION) ;
        }
    }
   
   
    public String cancelSingleInvoice(Number eInvoiceId){
          OracleCallableStatement callableStatement = null;   
          try   
          {   
               String callProc = " BEGIN XXXT_EARCHIVE_SCREEN_PKG.cancel_invoice(:1,:2,:3); END; ";   
               callableStatement = (OracleCallableStatement)getOADBTransaction().createCallableStatement(callProc,1);   
               callableStatement.setInt(1, eInvoiceId.intValue());     
               callableStatement.registerOutParameter(2,Types.VARCHAR);   
               callableStatement.registerOutParameter(3,Types.VARCHAR);      
               callableStatement.execute();   
               String status = (String)callableStatement.getString(2);   
               String error  = (String)callableStatement.getString(3);   
               if("ERROR".equals(status)){
                 return error ;
               }else{
                return null ;
              }
          }   
          catch(Exception e)   
          {   
               e.printStackTrace();   
               return "Prosedur cagirisinda Hata - XXXT_EARCHIVE_SCREEN_PKG.cancel_invoice";
          }finally   
          {   
               try   
               {   
                    callableStatement.close();   
               }   
               catch(Exception exception2)   
               {   
                    throw OAException.wrapperException(exception2);   
               }   
          }   
    }
    public void updateEmailOfInvoices(OAPageContext pageContext) throws OAException{
        EArchiveHeaderVOImpl resultVO =getEArchiveHeaderVO1() ;
        RowSetIterator rsi = resultVO.createRowSetIterator("RSI");
        int selectedCount=0 ;
        for(EArchiveHeaderVORowImpl row =(EArchiveHeaderVORowImpl)rsi.first();row!=null ; row =(EArchiveHeaderVORowImpl)rsi.next()){
           if ("Y".equals(row.getSelectedFlag())){
               selectedCount++;
           }
        }
        rsi.closeRowSetIterator();
        if(selectedCount == 0 ){
             throw new OAException("\u0130\u015Flem i\u00E7in l\u00FCtfen sat\u0131r se\u00E7iniz .",OAException.ERROR) ;
        }
        
        rsi = resultVO.createRowSetIterator("RSI2");
        boolean errorStat =false ;
        for(EArchiveHeaderVORowImpl row =(EArchiveHeaderVORowImpl)rsi.first();row!=null ; row =(EArchiveHeaderVORowImpl)rsi.next()){
            String error  =updateEmailOfSingleInvoice(row.getEinvInvoiceId().intValue()) ;
            getTransaction().commit();
            if(error !=null){
               pageContext.putDialogMessage(new OAException("Fatura No :"+row.getInvoiceId()+" Hata:"+error,OAException.ERROR));
               errorStat=true;
            }
        }
        if(!errorStat){
            resultVO.executeQuery();
            throw new OAException("Eposta g\u00FCncelleme  \u0130\u015Flemi ba\u015Far\u0131yla sonu\u00E7land\u0131.",OAException.INFORMATION) ;
        }
    }
    
    public String  updateEmailOfSingleInvoice(Number eInvoiceId){
          OracleCallableStatement callableStatement = null;   
          try   
          {   
               String callProc = " BEGIN XXXT_EARCHIVE_SCREEN_PKG.update_email(:1,:2,:3); END; ";   
               callableStatement = (OracleCallableStatement)getOADBTransaction().createCallableStatement(callProc,1);   
               callableStatement.setInt(1, eInvoiceId.intValue());     
               callableStatement.registerOutParameter(2,Types.VARCHAR);   
               callableStatement.registerOutParameter(3,Types.VARCHAR);      
               callableStatement.execute();   
               String status = (String)callableStatement.getString(2);   
               String error  = (String)callableStatement.getString(3);   
               if("ERROR".equals(status)){
                 return error ;
               }else{
                 return null ;
               }
          }   
          catch(Exception e)   
          {   
               e.printStackTrace();   
               return "Prosedur cagirisinda Hata - XXXT_EARCHIVE_SCREEN_PKG.update_email";
          }finally   
          {   
               try   
               {   
                    callableStatement.close();   
               }   
               catch(Exception exception2)   
               {   
                    throw OAException.wrapperException(exception2);   
               }   
          }   
    }
   
   
    public void getEInvoicePDF(Number eInvoiceId, 
                                   HttpServletResponse response) {
            InputStream inputStream = null;
            OracleCallableStatement callableStatement = null;
            try {
                String callProc = 
                    " BEGIN XXXT_EARCHIVE_SCREEN_PKG.get_einvoice_pdf(:1,:2,:3,:4,:5,:6); END; ";
                callableStatement = 
                        (OracleCallableStatement)getOADBTransaction().createCallableStatement(callProc, 
                                                                                              1);
                callableStatement.setInt(1, eInvoiceId.intValue());
                callableStatement.registerOutParameter(2, Types.VARCHAR);
                callableStatement.registerOutParameter(3, Types.VARCHAR);
                callableStatement.registerOutParameter(4, Types.BLOB);
                callableStatement.registerOutParameter(5, Types.VARCHAR);
                callableStatement.registerOutParameter(6,  Types.VARCHAR);
                callableStatement.execute();
                String fileName = (String)callableStatement.getString(2);
                String fileType = (String)callableStatement.getString(3);
                Blob blob = callableStatement.getBLOB(4);
                String status = (String)callableStatement.getString(5);
                String error = (String)callableStatement.getString(6);
                if ("ERROR".equals(status)) {
                    throw new OAException("error" + error, 
                                          OAException.ERROR); //return error;
                } else {
                    ;
                }
                inputStream = blob.getBinaryStream();
                System.out.println("inputStream:" + inputStream);
                try {
                    //HttpServletResponse response= (HttpServletResponse)pageContext.getRenderingContext().getServletResponse();
                    response.setContentType(fileType);
                    String lo_contentDisposition = 
                        "attachment;filename=" + eInvoiceId.intValue() + ".pdf";
                    response.setHeader("Content-Disposition", 
                                       lo_contentDisposition);
                    javax.servlet.ServletOutputStream ostream = 
                        response.getOutputStream();
                    int bytesRead = -1;
                    byte[] buffer = new byte[1024];
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        ostream.write(buffer, 0, bytesRead);
                    }

                    inputStream.close();
                    ostream.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        
    public void cancelDraftInvoices(OAPageContext pageContext) throws OAException{
        EArchiveHeaderVOImpl resultVO =getEArchiveHeaderVO1() ;
        RowSetIterator rsi = resultVO.createRowSetIterator("RSI");
        int selectedCount=0 ;
        for(EArchiveHeaderVORowImpl row =(EArchiveHeaderVORowImpl)rsi.first();row!=null;row =(EArchiveHeaderVORowImpl)rsi.next()){
           System.out.println("row.getSelectedFlag():"+row.getSelectedFlag());
           if ("Y".equals(row.getSelectedFlag())){
               selectedCount++;
           }
        }
        rsi.closeRowSetIterator();
        if(selectedCount == 0 ){
             throw new OAException("\u0130\u015Flem i\u00E7in l\u00FCtfen sat\u0131r se\u00E7iniz .",OAException.ERROR) ;
        }
        
        rsi = resultVO.createRowSetIterator("RSI2");
        boolean errorStat =false ;
        for(EArchiveHeaderVORowImpl row =(EArchiveHeaderVORowImpl)rsi.first();row!=null ; row =(EArchiveHeaderVORowImpl)rsi.next()){
            String error  =cancelSingleDraftInvoice(row.getEinvInvoiceId().intValue()) ;
            getTransaction().commit();
            if(error!=null){
               pageContext.putDialogMessage(new OAException("Fatura No :"+row.getInvoiceId()+" Hata:"+error,OAException.ERROR));
               errorStat=true;
            }
        }
        rsi.closeRowSetIterator();
        if(!errorStat){
            resultVO.executeQuery();
            throw new OAException("Taslak iptal islemi basariyla sonuclandi.",OAException.INFORMATION) ;
        }
    }
    
    
    public String cancelSingleDraftInvoice(Number eInvoiceId){
          OracleCallableStatement callableStatement = null;   
          try   
          {   
               String callProc = " BEGIN XXXT_EARCHIVE_SCREEN_PKG.cancel_draft_invoice(:1,:2,:3); END; ";   
               callableStatement = (OracleCallableStatement)getOADBTransaction().createCallableStatement(callProc,1);   
               callableStatement.setInt(1, eInvoiceId.intValue());     
               callableStatement.registerOutParameter(2,Types.VARCHAR);   
               callableStatement.registerOutParameter(3,Types.VARCHAR);      
               callableStatement.execute(); 
               
               String status = (String)callableStatement.getString(2);   
               String error  = (String)callableStatement.getString(3);  
               if("ERROR".equals(status)){
                 return error ;
               }else{
                 return null ;
               }
          }   
          catch(Exception e)   
          {   
               e.printStackTrace();   
               return "Prosedur cagirisinda Hata - XXXT_EARCHIVE_SCREEN_PKG.cancel_draft_invoice";
          }finally   
          {   
               try   
               {   
                    callableStatement.close();   
               }   
               catch(Exception exception2)   
               {   
                    throw OAException.wrapperException(exception2);   
               }
               
          }
        
    }
   
   
    /**This is the default constructor (do not remove)
     */
    public EArchiveAMImpl() {
    }

    /**Sample main for debugging Business Components code using the tester.
     */
    public static void main(String[] args) {
        launchTester("xxxt.oracle.apps.xxxt.einvoice.earchive.server", /* package name */
      "EArchiveAMLocal" /* Configuration Name */);
    }


    /**Container's getter for EArchiveSearchPanelVO1
     */
    public EArchiveSearchPanelVOImpl getEArchiveSearchPanelVO1() {
        return (EArchiveSearchPanelVOImpl)findViewObject("EArchiveSearchPanelVO1");
    }

    /**Container's getter for EArchiveStatusesVO1
     */
    public EArchiveStatusesVOImpl getEArchiveStatusesVO1() {
        return (EArchiveStatusesVOImpl)findViewObject("EArchiveStatusesVO1");
    }

    /**Container's getter for EArchiveSourcesVO1
     */
    public EArchiveSourcesVOImpl getEArchiveSourcesVO1() {
        return (EArchiveSourcesVOImpl)findViewObject("EArchiveSourcesVO1");
    }

    /**Container's getter for EArchiveHeaderVO1
     */
    public EArchiveHeaderVOImpl getEArchiveHeaderVO1() {
        return (EArchiveHeaderVOImpl)findViewObject("EArchiveHeaderVO1");
    }

    /**Container's getter for EArchiveLinesVO1
     */
    public EArchiveLinesVOImpl getEArchiveLinesVO1() {
        return (EArchiveLinesVOImpl)findViewObject("EArchiveLinesVO1");
    }

    /**Container's getter for EArchiveHeaderToLineVL1
     */
    public ViewLinkImpl getEArchiveHeaderToLineVL1() {
        return (ViewLinkImpl)findViewLink("EArchiveHeaderToLineVL1");
    }

    /**Container's getter for EArchiveNotesVO1
     */
    public EArchiveNotesVOImpl getEArchiveNotesVO1() {
        return (EArchiveNotesVOImpl)findViewObject("EArchiveNotesVO1");
    }

    /**Container's getter for EArchiveHeaderToNoteVL1
     */
    public ViewLinkImpl getEArchiveHeaderToNoteVL1() {
        return (ViewLinkImpl)findViewLink("EArchiveHeaderToNoteVL1");
    }
}
