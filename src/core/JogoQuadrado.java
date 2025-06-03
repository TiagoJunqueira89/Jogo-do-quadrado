
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JogoQuadrado {

    // Tamanho do quadrado grande
    static final int TAMANHO_QUADRADO_GRANDE = 400;
    // Tamanho do quadrado pequeno
    static final int TAMANHO_QUADRADO_PEQUENO = 50;
    
    // Posição inicial do quadrado pequeno
    int posX = (TAMANHO_QUADRADO_GRANDE - TAMANHO_QUADRADO_PEQUENO) / 2;
    int posY = (TAMANHO_QUADRADO_GRANDE - TAMANHO_QUADRADO_PEQUENO) / 2;
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new JogoQuadrado().criarTela();
        });
    }
    
    public void criarTela() {
        // Criação da janela do jogo
        JFrame frame = new JFrame("Jogo do Quadrado");
        frame.setSize(TAMANHO_QUADRADO_GRANDE, TAMANHO_QUADRADO_GRANDE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Criação do painel onde o desenho será realizado
        JPanel painel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                desenharQuadrados(g);  // Método para desenhar os quadrados
            }
        };

        painel.setBackground(Color.WHITE);
        frame.add(painel);
        
        // Adiciona um listener de teclas para mover o quadrado pequeno
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                // Move o quadrado pequeno nas direções permitidas
                if (keyCode == KeyEvent.VK_UP) {
                    if (posY > 0) posY -= 10;  // Mover para cima
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    if (posY < TAMANHO_QUADRADO_GRANDE - TAMANHO_QUADRADO_PEQUENO) posY += 10;  // Mover para baixo
                } else if (keyCode == KeyEvent.VK_LEFT) {
                    if (posX > 0) posX -= 10;  // Mover para a esquerda
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    if (posX < TAMANHO_QUADRADO_GRANDE - TAMANHO_QUADRADO_PEQUENO) posX += 10;  // Mover para a direita
                }
                painel.repaint();  // Atualiza o painel desenhado
            }
        });

        frame.setVisible(true);  // Torna a janela visível
    }
    
    // Método para desenhar os quadrados no painel
    public void desenharQuadrados(Graphics g) {
        // Desenha o quadrado grande (área onde o quadrado pequeno pode se mover)
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, TAMANHO_QUADRADO_GRANDE, TAMANHO_QUADRADO_GRANDE);
        
        // Desenha o quadrado pequeno, que pode ser movido
        g.setColor(Color.RED);
        g.fillRect(posX, posY, TAMANHO_QUADRADO_PEQUENO, TAMANHO_QUADRADO_PEQUENO);
    }
}
