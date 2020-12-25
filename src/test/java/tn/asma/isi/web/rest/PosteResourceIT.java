package tn.asma.isi.web.rest;

import tn.asma.isi.JhipsterApp;
import tn.asma.isi.domain.Poste;
import tn.asma.isi.repository.PosteRepository;
import tn.asma.isi.service.PosteService;
import tn.asma.isi.service.dto.PosteDTO;
import tn.asma.isi.service.mapper.PosteMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PosteResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PosteResourceIT {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_DEPARTEMENT = "AAAAAAAAAA";
    private static final String UPDATED_DEPARTEMENT = "BBBBBBBBBB";

    private static final Integer DEFAULT_SALAIRE = 1;
    private static final Integer UPDATED_SALAIRE = 2;

    @Autowired
    private PosteRepository posteRepository;

    @Autowired
    private PosteMapper posteMapper;

    @Autowired
    private PosteService posteService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPosteMockMvc;

    private Poste poste;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Poste createEntity(EntityManager em) {
        Poste poste = new Poste()
            .nom(DEFAULT_NOM)
            .departement(DEFAULT_DEPARTEMENT)
            .salaire(DEFAULT_SALAIRE);
        return poste;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Poste createUpdatedEntity(EntityManager em) {
        Poste poste = new Poste()
            .nom(UPDATED_NOM)
            .departement(UPDATED_DEPARTEMENT)
            .salaire(UPDATED_SALAIRE);
        return poste;
    }

    @BeforeEach
    public void initTest() {
        poste = createEntity(em);
    }

    @Test
    @Transactional
    public void createPoste() throws Exception {
        int databaseSizeBeforeCreate = posteRepository.findAll().size();
        // Create the Poste
        PosteDTO posteDTO = posteMapper.toDto(poste);
        restPosteMockMvc.perform(post("/api/postes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(posteDTO)))
            .andExpect(status().isCreated());

        // Validate the Poste in the database
        List<Poste> posteList = posteRepository.findAll();
        assertThat(posteList).hasSize(databaseSizeBeforeCreate + 1);
        Poste testPoste = posteList.get(posteList.size() - 1);
        assertThat(testPoste.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testPoste.getDepartement()).isEqualTo(DEFAULT_DEPARTEMENT);
        assertThat(testPoste.getSalaire()).isEqualTo(DEFAULT_SALAIRE);
    }

    @Test
    @Transactional
    public void createPosteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = posteRepository.findAll().size();

        // Create the Poste with an existing ID
        poste.setId(1L);
        PosteDTO posteDTO = posteMapper.toDto(poste);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPosteMockMvc.perform(post("/api/postes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(posteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Poste in the database
        List<Poste> posteList = posteRepository.findAll();
        assertThat(posteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = posteRepository.findAll().size();
        // set the field null
        poste.setNom(null);

        // Create the Poste, which fails.
        PosteDTO posteDTO = posteMapper.toDto(poste);


        restPosteMockMvc.perform(post("/api/postes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(posteDTO)))
            .andExpect(status().isBadRequest());

        List<Poste> posteList = posteRepository.findAll();
        assertThat(posteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDepartementIsRequired() throws Exception {
        int databaseSizeBeforeTest = posteRepository.findAll().size();
        // set the field null
        poste.setDepartement(null);

        // Create the Poste, which fails.
        PosteDTO posteDTO = posteMapper.toDto(poste);


        restPosteMockMvc.perform(post("/api/postes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(posteDTO)))
            .andExpect(status().isBadRequest());

        List<Poste> posteList = posteRepository.findAll();
        assertThat(posteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSalaireIsRequired() throws Exception {
        int databaseSizeBeforeTest = posteRepository.findAll().size();
        // set the field null
        poste.setSalaire(null);

        // Create the Poste, which fails.
        PosteDTO posteDTO = posteMapper.toDto(poste);


        restPosteMockMvc.perform(post("/api/postes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(posteDTO)))
            .andExpect(status().isBadRequest());

        List<Poste> posteList = posteRepository.findAll();
        assertThat(posteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPostes() throws Exception {
        // Initialize the database
        posteRepository.saveAndFlush(poste);

        // Get all the posteList
        restPosteMockMvc.perform(get("/api/postes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(poste.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].departement").value(hasItem(DEFAULT_DEPARTEMENT)))
            .andExpect(jsonPath("$.[*].salaire").value(hasItem(DEFAULT_SALAIRE)));
    }
    
    @Test
    @Transactional
    public void getPoste() throws Exception {
        // Initialize the database
        posteRepository.saveAndFlush(poste);

        // Get the poste
        restPosteMockMvc.perform(get("/api/postes/{id}", poste.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(poste.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.departement").value(DEFAULT_DEPARTEMENT))
            .andExpect(jsonPath("$.salaire").value(DEFAULT_SALAIRE));
    }
    @Test
    @Transactional
    public void getNonExistingPoste() throws Exception {
        // Get the poste
        restPosteMockMvc.perform(get("/api/postes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePoste() throws Exception {
        // Initialize the database
        posteRepository.saveAndFlush(poste);

        int databaseSizeBeforeUpdate = posteRepository.findAll().size();

        // Update the poste
        Poste updatedPoste = posteRepository.findById(poste.getId()).get();
        // Disconnect from session so that the updates on updatedPoste are not directly saved in db
        em.detach(updatedPoste);
        updatedPoste
            .nom(UPDATED_NOM)
            .departement(UPDATED_DEPARTEMENT)
            .salaire(UPDATED_SALAIRE);
        PosteDTO posteDTO = posteMapper.toDto(updatedPoste);

        restPosteMockMvc.perform(put("/api/postes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(posteDTO)))
            .andExpect(status().isOk());

        // Validate the Poste in the database
        List<Poste> posteList = posteRepository.findAll();
        assertThat(posteList).hasSize(databaseSizeBeforeUpdate);
        Poste testPoste = posteList.get(posteList.size() - 1);
        assertThat(testPoste.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testPoste.getDepartement()).isEqualTo(UPDATED_DEPARTEMENT);
        assertThat(testPoste.getSalaire()).isEqualTo(UPDATED_SALAIRE);
    }

    @Test
    @Transactional
    public void updateNonExistingPoste() throws Exception {
        int databaseSizeBeforeUpdate = posteRepository.findAll().size();

        // Create the Poste
        PosteDTO posteDTO = posteMapper.toDto(poste);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPosteMockMvc.perform(put("/api/postes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(posteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Poste in the database
        List<Poste> posteList = posteRepository.findAll();
        assertThat(posteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePoste() throws Exception {
        // Initialize the database
        posteRepository.saveAndFlush(poste);

        int databaseSizeBeforeDelete = posteRepository.findAll().size();

        // Delete the poste
        restPosteMockMvc.perform(delete("/api/postes/{id}", poste.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Poste> posteList = posteRepository.findAll();
        assertThat(posteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
