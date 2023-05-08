package org.example.generator.lambdas;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import org.example.util.CaseUtils;

import java.io.IOException;
import java.io.Writer;

public class MustacheLambda {

    public static class PascalCase implements Mustache.Lambda {
        @Override
        public void execute(Template.Fragment fragment, Writer out) throws IOException {
            out.write(CaseUtils.toPascalCase(fragment.execute()));
        }
    }


}
