package org.tdeccn.table.view.html;

import java.util.Iterator;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.calc.CalcResult;
import org.extremecomponents.table.calc.CalcUtils;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.view.html.BuilderConstants;
import org.extremecomponents.table.view.html.CalcBuilder;
import org.extremecomponents.util.ExtremeUtils;
import org.extremecomponents.util.HtmlBuilder;
import org.tdeccn.table.core.TDTableConstants;


public class TDCalcBuilder extends CalcBuilder{

    private HtmlBuilder html;
    private TableModel model;

    public TDCalcBuilder(TableModel model) {
        this(new HtmlBuilder(), model);
    }
    
    public TDCalcBuilder(HtmlBuilder html, TableModel model) {
    	super(html,model);
        this.html = html;
        this.model = model;
    }

    public void singleRowCalcResults() {
        html.tr(1).styleClass(BuilderConstants.CALC_ROW_CSS).close();
        
        String span=null;
        int columnIndex=-1;
        int maxI=1;
        
        for (Iterator iter = model.getColumnHandler().getColumns().iterator(); iter.hasNext();) {
            Column column = (Column) iter.next();
            
            columnIndex++;
            
//            int colIdx=column.g
            if (column.isFirstColumn()) {
            	span=column.getAttributeAsString(TDTableConstants.CALC_SPAN);
                if (span!=null){
                	span=span.trim();
                	try {
                		maxI=Integer.parseInt(span);
					} catch (Exception e) {
						maxI=1;
					}
                	
                }
                maxI=maxI<1?1:maxI;
                
                String calcTitle[] = CalcUtils.getFirstCalcColumnTitles(model);
                if (calcTitle != null && calcTitle.length > 0) {
                    html.td(2).styleClass(BuilderConstants.CALC_TITLE_CSS);
                    if (maxI>1){
                    	html.append(" colspan=\""+maxI+"\" ");
                    }
                    html.close();
                    for (int i = 0; i < calcTitle.length; i++) {
                        String title = calcTitle[i];
                        html.append(title);
                        if (calcTitle.length > 0 && i + 1 != calcTitle.length) {
                            html.append(" / ");
                        }
                    }
                    html.tdEnd();
                }

                continue;
            }
            if (columnIndex<maxI) continue;
            
            if (column.isCalculated()) {
                html.td(2).styleClass(BuilderConstants.CALC_RESULT_CSS).close();
                CalcResult calcResults[] = CalcUtils.getCalcResults(model, column);
                for (int i = 0; i < calcResults.length; i++) {
                    CalcResult calcResult = calcResults[i];
                    Number value = calcResult.getValue();
                    if (value == null) {
                        html.append(calcResult.getName());
                    } else {
                        html.append(ExtremeUtils.formatNumber(column.getFormat(), value, model.getLocale()));
                    }

                    if (calcResults.length > 0 && i + 1 != calcResults.length) {
                        html.append(" / ");
                    }
                }
            } else {
                html.td(2).close();
                html.nbsp();
            }

            html.tdEnd();
        }
        html.trEnd(1);
    }

    public void multiRowCalcResults() {
        Column firstCalcColumn = model.getColumnHandler().getFirstCalcColumn();
        int rows = firstCalcColumn.getCalc().length;


        
        
        for (int i = 0; i < rows; i++) {
            html.tr(1).styleClass(BuilderConstants.CALC_ROW_CSS).close();

            String span=null;
            int columnIndex=-1;
            int maxI=1;
            
            for (Iterator iter = model.getColumnHandler().getColumns().iterator(); iter.hasNext();) {
                Column column = (Column) iter.next();
                
                columnIndex++;
                
                if (column.isFirstColumn()) {
                	
                	span=column.getAttributeAsString(TDTableConstants.CALC_SPAN);
                    if (span!=null){
                    	span=span.trim();
                    	try {
                    		maxI=Integer.parseInt(span);
    					} catch (Exception e) {
    						maxI=1;
    					}
                    	
                    }
                    maxI=maxI<1?1:maxI;
                    
                    String calcTitle = CalcUtils.getFirstCalcColumnTitleByPosition(model, i);
                    html.td(2).styleClass(BuilderConstants.CALC_TITLE_CSS);
                    if (maxI>1){
                    	html.append(" colspan=\""+maxI+"\" ");
                    }
                    html.close();
                    html.append(calcTitle);
                    html.tdEnd();

                    continue;
                }

                if (columnIndex<maxI) continue;
                
                if (column.isCalculated()) {
                    html.td(2).styleClass(BuilderConstants.CALC_RESULT_CSS).close();
                    CalcResult calcResult = CalcUtils.getCalcResultsByPosition(model, column, i);
                    Number value = calcResult.getValue();
                    if (value == null) {
                        html.append(calcResult.getName());
                    } else {
                        html.append(ExtremeUtils.formatNumber(column.getFormat(), value, model.getLocale()));
                    }
                } else {
                    html.td(2).close();
                    html.nbsp();
                }

                html.tdEnd();
            }

            html.trEnd(1);
        }
    }
    
    public String toString() {
        return html.toString();
    }
}
