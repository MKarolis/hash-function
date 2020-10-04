package com.karolismed.hashfunction.hashing;

import static com.karolismed.hashfunction.hashing.HashingService.HASH_LENGTH;
import static org.assertj.core.api.Assertions.assertThat;

import com.karolismed.hashfunction.constants.TestResourceFilename;
import java.io.IOException;
import java.io.InputStream;
import org.junit.Before;
import org.junit.Test;

public class HashingServiceTest {

    HashingService hashingService;

    @Before
    public void setUp() {
        hashingService = new HashingService();
    }

    @Test
    public void hash_emptyString() {
        assertHashes("", "0");
    }

    @Test
    public void hash_singleCharacter() throws IOException {
        String str1 = readStringFromClasspath(TestResourceFilename.ONE_SYMBOL_1);
        String str2 = readStringFromClasspath(TestResourceFilename.ONE_SYMBOL_2);

        assertHashes(str1, str2);
    }

    @Test
    public void hash_largeString() throws IOException {
        String str1 = readStringFromClasspath(TestResourceFilename.WORD_LENGTH_1000_1);
        String str2 = readStringFromClasspath(TestResourceFilename.WORD_LENGTH_1000_2);

        assertHashes(str1, str2);
    }

    @Test
    public void hash_largeString_differsByOneChar() throws IOException {
        String str1 = readStringFromClasspath(TestResourceFilename.WORD_LENGTH_1000_SYMBOL_DIFF_1);
        String str2 = readStringFromClasspath(TestResourceFilename.WORD_LENGTH_1000_SYMBOL_DIFF_2);

        assertHashes(str1, str2);
    }

    private void assertHashes(String str1, String str2) {
        assertThat(hashingService.hash(str1))
            .satisfies(hash -> assertThat(hash.length()).isEqualTo(HASH_LENGTH))
            .isEqualTo(hashingService.hash(str1));
        assertThat(hashingService.hash(str2))
            .satisfies(hash -> assertThat(hash.length()).isEqualTo(HASH_LENGTH))
            .isEqualTo(hashingService.hash(str2));

        assertThat(hashingService.hash(str1)).isNotEqualTo(hashingService.hash(str2));
    }

    private String readStringFromClasspath(TestResourceFilename fileName) throws IOException {
        InputStream in = ClassLoader.getSystemClassLoader()
            .getResourceAsStream(fileName.toString());
        assertThat(in).isNotNull();

        return new String(in.readAllBytes());
    }
}
