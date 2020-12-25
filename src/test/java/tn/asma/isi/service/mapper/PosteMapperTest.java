package tn.asma.isi.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PosteMapperTest {

    private PosteMapper posteMapper;

    @BeforeEach
    public void setUp() {
        posteMapper = new PosteMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(posteMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(posteMapper.fromId(null)).isNull();
    }
}
