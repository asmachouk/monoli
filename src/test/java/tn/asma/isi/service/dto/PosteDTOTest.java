package tn.asma.isi.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import tn.asma.isi.web.rest.TestUtil;

public class PosteDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PosteDTO.class);
        PosteDTO posteDTO1 = new PosteDTO();
        posteDTO1.setId(1L);
        PosteDTO posteDTO2 = new PosteDTO();
        assertThat(posteDTO1).isNotEqualTo(posteDTO2);
        posteDTO2.setId(posteDTO1.getId());
        assertThat(posteDTO1).isEqualTo(posteDTO2);
        posteDTO2.setId(2L);
        assertThat(posteDTO1).isNotEqualTo(posteDTO2);
        posteDTO1.setId(null);
        assertThat(posteDTO1).isNotEqualTo(posteDTO2);
    }
}
