package com.example.tutoproject.opgame.view;


import com.example.tutoproject.opgame.service.OpGame;
import com.example.tutoproject.opgame.service.PlusOperatorLevelOne;

import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.SwingConstants;


import javax.swing.ImageIcon;
import java.awt.Font;

public class MainGUI extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Map<String, ImageIcon> charImages = new HashMap<>();
    private Map<String, JButton> keyButtons = new HashMap<>();
    private JLabel lblQuestion, lblAnswer, lblChar, lblCheeringUp;
    private OpGame game;

    public MainGUI() {

        // 격려캐릭터 이미지 준비(로드)
        loadCharImages();

        // 프레임(윈도우) 창크기 고정
        setSize(new Dimension(300, 500));
        setResizable(false);
        getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        // 문제와 답
        JPanel panelQuestion = new JPanel();
        panelQuestion.setPreferredSize(new Dimension(200, 40));
        panelQuestion.setSize(new Dimension(200, 80));
        getContentPane().add(panelQuestion);

        lblQuestion = new JLabel("1 + 1 = ");
        lblQuestion.setFont(new Font("굴림", Font.PLAIN, 24));
        panelQuestion.add(lblQuestion);

        lblAnswer = new JLabel("?");
        lblAnswer.setFont(new Font("굴림", Font.PLAIN, 24));
        panelQuestion.add(lblAnswer);

        // 격려캐릭터
        lblChar = new JLabel("");
        lblChar.setPreferredSize(new Dimension(200, 100));
        getContentPane().add(lblChar);

        changeCharImage("normal");

        // 격려문구
        lblCheeringUp = new JLabel("참 잘했어요~");
        lblCheeringUp.setFont(new Font("굴림", Font.PLAIN, 22));
        lblCheeringUp.setHorizontalAlignment(SwingConstants.CENTER);
        lblCheeringUp.setHorizontalTextPosition(SwingConstants.CENTER);
        lblCheeringUp.setPreferredSize(new Dimension(200, 30));
        getContentPane().add(lblCheeringUp);

        // 키패드 초기화
        initKeypadButtons();

        // 프레임(윈도우) 보이기
        setVisible(true);

        // 게임레벨 초기화와 문제출제
        game = new OpGame(new PlusOperatorLevelOne());
        nextQuestion();

    }

    /**
     * 격려캐릭터 이미지 변경
     * @param key 변경할 캐릭터이름: "normal"|"success"|"fail"
     */
    private void changeCharImage(String key) {
        lblChar.setIcon(charImages.get(key));
    }

    /**
     * 격려캐릭터 이미지 준비(로드)
     */
    private void loadCharImages() {
        String[][] imgResoruces = {
                {"normal", "C:\\Users\\MMBY777LUCKY\\IdeaProjects\\tutoinitSpring\\src\\main\\java\\com\\example\\tutoproject\\res\\images\\character_men.gif"},
                {"success", "C:\\Users\\MMBY777LUCKY\\IdeaProjects\\tutoinitSpring\\src\\main\\java\\com\\example\\tutoproject\\res\\images\\character_men_a.gif"},
                {"fail", "C:\\Users\\MMBY777LUCKY\\IdeaProjects\\tutoinitSpring\\src\\main\\java\\com\\example\\tutoproject\\res\\images\\character_men_b.gif"}
        };
        for (int i = 0; i < imgResoruces.length; i++) {
            //ImageIcon객체를 생성
            ImageIcon originIcon = new ImageIcon(MainGUI.class.getResource(imgResoruces[i][1]));
            //ImageIcon에서 Image를 추출
            Image originImg = originIcon.getImage();
            //추출된 Image의 크기를 조절하여 새로운 Image객체 생성
            Image changedImg= originImg.getScaledInstance(200, 100, Image.SCALE_SMOOTH );
            //새로운 Image로 ImageIcon객체를 생성
            ImageIcon icon = new ImageIcon(changedImg);
            charImages.put(imgResoruces[i][0], icon);
        }

    }

    /**
     * 키패드 초기화
     */
    private void initKeypadButtons() {
        MouseAdapter clickLstener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JButton btn = (JButton)e.getSource();
                String btnNum = btn.getActionCommand();
                lblAnswer.setText(btnNum);
                if ( game.isAnswer(Integer.parseInt(btnNum)) ) {
                    lblCheeringUp.setText("참 잘했어요");
                    changeCharImage("success");
                    nextQuestion();
                } else {
                    lblCheeringUp.setText(game.getCheeringUpMsg());
                    changeCharImage("fail");
                }
            }
        };

        for (int i = 0; i < 10; i++) {
            String bunNum = i + "";
            JButton button = new JButton( bunNum );
            button.setPreferredSize(new Dimension(80, 60));
            button.addMouseListener(clickLstener);
            button.setActionCommand( bunNum );
            keyButtons.put(bunNum, button);
        }

        JPanel panelKeypad = new JPanel();
        getContentPane().add(panelKeypad);
        panelKeypad.setLayout(new GridLayout(4, 3, 5, 5));

        panelKeypad.add(keyButtons.get("1"));
        panelKeypad.add(keyButtons.get("2"));
        panelKeypad.add(keyButtons.get("3"));
        panelKeypad.add(keyButtons.get("4"));
        panelKeypad.add(keyButtons.get("5"));
        panelKeypad.add(keyButtons.get("6"));
        panelKeypad.add(keyButtons.get("7"));
        panelKeypad.add(keyButtons.get("8"));
        panelKeypad.add(keyButtons.get("9"));
        panelKeypad.add(new JButton("메뉴"));
        panelKeypad.add(keyButtons.get("0"));
        panelKeypad.add(new JButton("다음"));
    }

    /**
     * 문제제출
     */
    private void nextQuestion() {
        game.makeQuestion(10);
        String question = game.getQuestion();
        lblQuestion.setText(question);
        lblAnswer.setText("?");
    }

    public static void main(String[] args) {
        new MainGUI();
    }

}
