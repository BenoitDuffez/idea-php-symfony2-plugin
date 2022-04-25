package fr.adrienbrault.idea.symfony2plugin.tests.translation.parser;

import com.intellij.openapi.vfs.VirtualFile;
import fr.adrienbrault.idea.symfony2plugin.tests.SymfonyLightCodeInsightFixtureTestCase;
import fr.adrienbrault.idea.symfony2plugin.translation.parser.TranslationPsiParser;
import fr.adrienbrault.idea.symfony2plugin.translation.parser.TranslationStringMap;

import java.util.Collections;

/**
 * @author Daniel Espendiller <daniel@espendiller.net>
 */
public class TranslationPsiParserTest extends SymfonyLightCodeInsightFixtureTestCase {
    public void setUp() throws Exception {
        super.setUp();
        myFixture.copyFileToProject("classes.php");
    }

    protected String getTestDataPath() {
        return "src/test/java/fr/adrienbrault/idea/symfony2plugin/tests/translation/parser/fixtures";
    }

    public void testCompiledTranslationParser() {
        VirtualFile virtualFile = myFixture.copyFileToProject("catalogue.af.X7ow_p+.php");

        TranslationPsiParser translationPsiParser1 = new TranslationPsiParser(getProject(), Collections.emptyList());
        translationPsiParser1.parse(virtualFile);

        TranslationStringMap translationStringMap = translationPsiParser1.parsePathMatcher();

        assertTrue(translationStringMap.getDomainMap("security").size() > 0);

        assertTrue(translationStringMap.getDomainMap("validators").contains("This value should be false."));
        assertTrue(translationStringMap.getDomainMap("validators").contains("This value should be false. (1)"));
    }
}