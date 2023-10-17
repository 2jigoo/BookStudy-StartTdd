package chap10;
import org.junit.jupiter.api.Test;
public class BulkLoaderTest {
    //프로젝트 기준으로 상대 경로를 사용한 테스트
    private String bulkFilePath = "src/test/resources/bulk.text";

    @Test
    void load(){
        BulkLoader loader = new BulkLoader();
    }
}
