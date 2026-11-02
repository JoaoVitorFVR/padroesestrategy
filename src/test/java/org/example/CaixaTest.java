package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class CaixaTest {

    @BeforeEach
    void setUp() {
        Caixa.getInstance().limpar();
    }

    @Test
    void deveRetornarSempreAMesmaInstancia() {
        assertSame(Caixa.getInstance(), Caixa.getInstance());
    }

    @Test
    void construtorDeveSerPrivado() throws Exception {
        Constructor<Caixa> construtor = Caixa.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(construtor.getModifiers()));
    }

    @Test
    void deveIniciarZerado() {
        assertEquals(0.0f, Caixa.getInstance().getTotal());
        assertEquals(0, Caixa.getInstance().getQuantidade());
    }

    @Test
    void deveRegistrarValores() {
        Caixa.getInstance().registrar(50.0f);
        Caixa.getInstance().registrar(25.0f);
        assertEquals(75.0f, Caixa.getInstance().getTotal());
        assertEquals(2, Caixa.getInstance().getQuantidade());
    }

    @Test
    void deveCompartilharRegistrosEntreChamadas() {
        Caixa.getInstance().registrar(10.0f);
        Caixa outra = Caixa.getInstance();
        assertEquals(10.0f, outra.getTotal());
    }

    @Test
    void deveLimparOCaixa() {
        Caixa.getInstance().registrar(10.0f);
        Caixa.getInstance().limpar();
        assertEquals(0.0f, Caixa.getInstance().getTotal());
        assertEquals(0, Caixa.getInstance().getQuantidade());
    }
}
