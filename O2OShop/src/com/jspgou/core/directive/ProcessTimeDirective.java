package com.jspgou.core.directive;

import com.jspgou.common.web.freemarker.MustNumberException;
import com.jspgou.cms.action.directive.abs.WebDirective;
import freemarker.core.Environment;
import freemarker.template.*;
import java.io.IOException;
import java.io.Writer;
import java.text.DecimalFormat;
import java.util.Map;
/**
* This class should preserve.
* @preserve
*/
public class ProcessTimeDirective extends WebDirective{
	
    private static final DecimalFormat FORMAT = new DecimalFormat("0.000");

    @SuppressWarnings("unchecked")
	public void execute(Environment env, Map params, 
			TemplateModel[] atemplatemodel, TemplateDirectiveBody body)
        throws TemplateException, IOException{
        long time = getStartTime(env);
        if(time == -1L){
            return;
        } else{
        	time = System.currentTimeMillis() - time;
            Writer writer = env.getOut();
            writer.append("Processed in "+FORMAT.format((float)time / 1000.0F)+" second(s)");
            return;
        }
    }

    private long getStartTime(Environment env)
        throws TemplateModelException{
        TemplateModel templatemodel = env.getGlobalVariable("_start_time");
        if(templatemodel == null){
            return -1L;
        }
        if(templatemodel instanceof TemplateNumberModel){
            return ((TemplateNumberModel)templatemodel).getAsNumber().longValue();
        }else{
            throw new MustNumberException("_start_time");
        }
    }

}
