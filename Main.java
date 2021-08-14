import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Main extends JFrame {
	static JButton startTimer, enterB;
	static JLabel dTime, homePoints, awayPoints, c, s, homeRed, awayRed;
	public static int seconds, minutes, time, max, hp = 0, ap = 0, sets = 1, hs = 0, as = 0, homeRedCard = 0,
			awayRedCard = 0, winningSet, winningScore, inTime;
	static String print = "", input, command, se, homeP, awayP, HRC = "", ARC = "", inputTime, homeSets, awaySets;
	static Timer timer = new Timer();
	protected static JFrame frame;
	static TheTimer timeboi = new TheTimer();
	static boolean[] sportChosen = new boolean[6];
	static JButton[] blank = new JButton[20];
	static JTextField scoreAdder;

	public static class TheTimer {
		static TimerTask task = new TimerTask() {
			public void run() {
				seconds++;
				if(seconds == 60){
					seconds = 0;
					minutes++;
				}
				if(minutes < 10){
					print = "0";
					print += minutes + ":";
					if(seconds < 10){
						print += "0" + seconds;
					}
					else{
						print += seconds;
					}
				}
				else{
					print = minutes + ":";
					if(seconds < 10){
						print += "0" + seconds;
					}
					else{
						print += seconds;
					}
				}
				dTime.setText(print);
				if(minutes + 1 == max && seconds + 1 == 60){
					onesec();
					print = minutes + 1 + ":00";
					dTime.setText(print);
					seconds = 0;
					print = minutes + "01";
					minutes += 1;
					timer.cancel();
					startTimer.setText("Resume");

					if(sportChosen[0]){
						max = 90;
						sets++;
						if(minutes == 45){
							se = Integer.toString(sets);
							s.setText("Half: " + se);
						}
					}
					else if(sportChosen[2]){
						max += 10;
						if(minutes != 60){
							se = Integer.toString(sets);
							s.setText("Quarter: " + se);
						}
					}
					else{
						max += inTime;
					}
					if(max == 90 && sportChosen[0]){
						if(hp > ap){
							System.out.println("Home team wins");
						}
						else if(ap > hp){
							System.out.println("Away team wins");
						}
						else{
							System.out.println("The game is tied");
						}
					}
				}
			}
		};

		public static void onesec() {
			try{
				Thread.sleep(1000);
			}
			catch(Exception e){
			}
		}

		public void start() {
			timer.scheduleAtFixedRate(task, 1000, 1000);
		}

		public void pause() {
			startTimer.setText("Resume");
			timer.cancel();
		}

		public void resume() {
			startTimer.setText("Pause");
			timer = new Timer();
			task = new TimerTask() {
				public void run() {
					seconds++;
					if(seconds == 60){
						seconds = 0;
						minutes++;
					}
					if(minutes < 10){
						print = "0";
						print += minutes + ":";
						if(seconds < 10){
							print += "0" + seconds;
						}
						else{
							print += seconds;
						}
					}
					else{
						print = minutes + ":";
						if(seconds < 10){
							print += "0" + seconds;
						}
						else{
							print += seconds;
						}
					}
					dTime.setText(print);
					if(minutes + 1 == max && seconds + 1 == 60){
						onesec();
						minutes += 1;
						print = minutes + ":00";
						dTime.setText(print);
						seconds = 0;
						print = minutes + "01";
						minutes += 1;
						timer.cancel();
						startTimer.setText("Resume");
						if(sportChosen[0]){
							max = 90;
							sets++;
							if(minutes == 45){
								se = Integer.toString(sets);
								s.setText("Half: " + se);
							}
						}
						else if(sportChosen[2]){
							max += 10;
							if(minutes != 60){
								se = Integer.toString(sets);
								s.setText("Quarter: " + se);
							}
						}
						else{
							max += inTime;
						}
						if(max == 90 && sportChosen[0]){
							if(hp > ap){
								System.out.println("Home team wins");
							}
							else if(ap > hp){
								System.out.println("Away team wins");
							}
							else{
								System.out.println("The game is tied");
							}
						}
					}
				}
			};
			timeboi.start();
		}
	}

	public static void gui() {
		frame = new JFrame();
		frame.setSize(650, 500);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void createFrame() {
		frame.getContentPane().removeAll();
		frame.repaint();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(650, 500);
	}

	public static void inputScore(String command, boolean[] sportChosen) {
		if(command.equals("1")){
			hp++;
			homeP = Integer.toString(hp);
			homePoints.setText(homeP);
		}
		else if(command.equals("-1")){
			hp--;
			homeP = Integer.toString(hp);
			homePoints.setText(homeP);
		}
		else if(command.equals("2")){
			ap++;
			awayP = Integer.toString(ap);
			awayPoints.setText(awayP);
		}
		else if(command.equals("-2")){
			ap--;
			awayP = Integer.toString(ap);
			awayPoints.setText(awayP);
		}
		else if(command.charAt(0) == '1' && command.length() > 1){
			if(command.charAt(1) == '='){
				String num = "";
				for(int i = 2; i < command.length(); i++){
					num += command.charAt(i);
				}
				int n = Integer.parseInt(num);
				hp = n;
				homeP = Integer.toString(hp);
				homePoints.setText(homeP);
			}
		}
		else if(command.charAt(0) == '2' && command.length() > 1){
			if(command.charAt(1) == '='){
				String num = "";
				for(int i = 2; i < command.length(); i++){
					num += command.charAt(i);
				}
				int n = Integer.parseInt(num);
				ap = n;
				awayP = Integer.toString(ap);
				awayPoints.setText(awayP);
			}
		}
		if(sportChosen[0]){
			if(command.equals("~1")){
				homeRedCard++;
				HRC = Integer.toString(homeRedCard);
				homeRed.setText("Red Cards: " + HRC);
			}
			else if(command.equals("~2")){
				awayRedCard++;
				ARC = Integer.toString(awayRedCard);
				awayRed.setText("Red Cards: " + ARC);
			}
		}
		if(sportChosen[2]){
			if(command.equals("11")){
				hp += 2;
				homeP = Integer.toString(hp);
				homePoints.setText(homeP);
			}
			else if(command.equals("111")){
				hp += 3;
				homeP = Integer.toString(hp);
				homePoints.setText(homeP);
			}
			else if(command.equals("22")){
				ap += 2;
				awayP = Integer.toString(ap);
				awayPoints.setText(awayP);
			}
			else if(command.equals("222")){
				ap += 3;
				awayP = Integer.toString(ap);
				awayPoints.setText(awayP);
			}
		}
	}

	public static void main(String[] args) {
		gui();
		frame.setLayout(new GridLayout(3, 3, 6, 6));
		JButton[] b = new JButton[6];
		JLabel l = new JLabel("                    Select A Sport:");
		b[0] = new JButton("Soccer");
		b[1] = new JButton("Volleyball");
		b[2] = new JButton("Basketball");
		b[3] = new JButton("Table Tennis");
		b[4] = new JButton("Badminton");
		b[5] = new JButton("Custom");
		for(int i = 0; i < 3; i++){
			blank[i] = new JButton("");
		}
		for(int i = 0; i < 3; i++){
			if(i == 1){
				frame.add(l);
			}
			else{
				blank[i].setOpaque(false);
				blank[i].setContentAreaFilled(false);
				blank[i].setBorderPainted(false);
				frame.add(blank[i]);
			}

		}
		for(int i = 0; i < 6; i++){
			frame.add(b[i]);
		}
		b[0].addActionListener(new ActionSoccer());
		b[1].addActionListener(new ActionVolleyball());
		b[2].addActionListener(new ActionBasketball());
		b[3].addActionListener(new ActionTableTennis());
		b[4].addActionListener(new ActionBadminton());
		b[5].addActionListener(new ActionCustom());
	}

	static class ActionTimer implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Start Time")){
				timeboi.start();
				startTimer.setText("Pause");
			}
			else if(e.getActionCommand().equals("Pause")){
				timeboi.pause();
			}
			else if(e.getActionCommand().equals("Resume")){
				timeboi.resume();
			}
		}
	}

	static class ActionEnter implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String command = scoreAdder.getText();
			scoreAdder.setText("");
			if(sportChosen[3] || sportChosen[4] || sportChosen[1]){
				if(hp == winningScore && ap < winningScore - 1 || ap == winningScore && hp < winningScore - 1){
					if(hp == winningScore){
						hs++;
						sets++;
						if(sets == 3 && sportChosen[1]){
							winningScore = 15;
						}
						hp = 0;
						homeP = Integer.toString(hp);
						homePoints.setText(homeP);
						ap = 0;
						awayP = Integer.toString(ap);
						awayPoints.setText(awayP);
						System.out.println("Home wins set");
						if(sportChosen[1]){
							homeSets = Integer.toString(hs);
							s.setText(homeSets + " : " + awaySets);
						}
						else{
							se = Integer.toString(sets);
							s.setText("Set: " + se);
						}
					}
					else{
						as++;
						sets++;
						if(sets == 3 && sportChosen[1]){
							winningScore = 15;
						}
						hp = 0;
						homeP = Integer.toString(hp);
						homePoints.setText(homeP);
						ap = 0;
						awayP = Integer.toString(ap);
						awayPoints.setText(awayP);
						System.out.println("Away wins set");
						if(sportChosen[1]){
							awaySets = Integer.toString(as);
							s.setText(homeSets + " : " + awaySets);
						}
						else{
							se = Integer.toString(sets);
							s.setText("Set: " + se);
						}
					}
				}
				else if(hp == winningScore - 1 && ap < winningScore - 1
						|| ap == winningScore - 1 && hp < winningScore - 1){
					inputScore(command, sportChosen);
					if(hp == winningScore && ap < winningScore - 1){
						hs++;
						sets++;
						if(sets == 3 && sportChosen[1]){
							winningScore = 15;
						}
						hp = 0;
						homeP = Integer.toString(hp);
						homePoints.setText(homeP);
						ap = 0;
						awayP = Integer.toString(ap);
						awayPoints.setText(awayP);
						System.out.println("Home wins set");
						if(sportChosen[1]){
							homeSets = Integer.toString(hs);
							s.setText(homeSets + " : " + awaySets);
						}
						else{
							se = Integer.toString(sets);
							s.setText("Set: " + se);
						}
					}
					else if(ap == winningScore && hp < winningScore - 1){
						as++;
						sets++;
						if(sets == 3 && sportChosen[1]){
							winningScore = 15;
						}
						hp = 0;
						homeP = Integer.toString(hp);
						homePoints.setText(homeP);
						ap = 0;
						awayP = Integer.toString(ap);
						awayPoints.setText(awayP);
						System.out.println("Away wins set");
						if(sportChosen[1]){
							awaySets = Integer.toString(as);
							s.setText(homeSets + " : " + awaySets);
						}
						else{
							se = Integer.toString(sets);
							s.setText("Set: " + se);
						}
					}
				}
				else if(hp < winningScore - 1 && ap < winningScore - 1){
					inputScore(command, sportChosen);
				}
				else if(hp >= winningScore - 1 && ap >= winningScore - 1){
					inputScore(command, sportChosen);
					if(hp - ap == 2){
						hs++;
						sets++;
						if(sets == 3 && sportChosen[1]){
							winningScore = 15;
						}
						if(hs != winningSet || as != winningSet){
							hp = 0;
							homeP = Integer.toString(hp);
							homePoints.setText(homeP);
							ap = 0;
							awayP = Integer.toString(ap);
							awayPoints.setText(awayP);
							System.out.println("Home wins set");
							if(sportChosen[1]){
								homeSets = Integer.toString(hs);
								s.setText(homeSets + " : " + awaySets);
							}
							else{
								se = Integer.toString(sets);
								s.setText("Set: " + se);
							}
						}
					}
					else if(hp - ap == -2){
						as++;
						sets++;
						if(sets == 3 && sportChosen[1]){
							winningScore = 15;
						}
						if(hs != winningSet || as != winningSet){
							hp = 0;
							homeP = Integer.toString(hp);
							homePoints.setText(homeP);
							ap = 0;
							awayP = Integer.toString(ap);
							awayPoints.setText(awayP);
							System.out.println("Away wins set");
							if(sportChosen[1]){
								awaySets = Integer.toString(as);
								s.setText(homeSets + " : " + awaySets);
							}
							else{
								se = Integer.toString(sets);
								s.setText("Set: " + se);
							}
						}
					}
				}
				if(hs == winningSet && as < winningSet){
					System.out.println("Home wins game");
				}
				else if(as == winningSet && hs < winningSet){
					System.out.println("Away wins game");
				}
			}
			else{
				inputScore(command, sportChosen);
			}
		}
	}

	static class ActionSoccer extends JFrame implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent a) {
			sportChosen[0] = true;
			System.out.println("Soccer Rules:");
			System.out.println("The game consists of two halves of 45 minutes each.");
			System.out.println("During play, red cards can be given and stoppage time can be added.");
			System.out.println(
					"If the score is tied after 90 minutes of play, the game goes to overtime, which is two halves of 15 minutes.");
			System.out.println(
					"If the score is still tied then the game goes to penalties. Each team gets 5 shots and the one that scores the most wins.");
			System.out.println();
			System.out.println("Your commands:                       Function:");
			System.out.println("\"1\"                                Add score to home team");
			System.out.println("\"-1\"                               Take away score from home team");
			System.out.println("\"2\"                                Add score to away team");
			System.out.println("\"-2\"                               Take away score from away team");
			System.out.println("\"~1\"                               Add a red card to home team");
			System.out.println("\"~2\"                               Add a red card to away team");
			System.out.println(
					"\"(team number)=(number)\"           Set (team number)'s score, where 1 is home team and 2 is away team, to (number)");
			minutes = 0;
			seconds = 0;
			sets = 1;
			max = 45;
			createFrame();
			frame.setLayout(null);
			startTimer = new JButton("Start Time");
			if(minutes < 10){
				print = "0";
				print += minutes + ":";
				if(seconds < 10){
					print += "0" + seconds;
				}
				else{
					print += seconds;
				}
			}
			else{
				print = minutes + ":";
				if(seconds < 10){
					print += "0" + seconds;
				}
				else{
					print += seconds;
				}
			}

			// time
			dTime = new JLabel(print);
			dTime.setFont(new Font("Serif", Font.PLAIN, 55));
			dTime.setBounds(260, 5, 200, 200);
			frame.add(dTime);

			// home score
			homePoints = new JLabel("Home Score");
			homePoints.setFont(new Font("Serif", Font.PLAIN, 40));
			homePoints.setBounds(20, 80, 200, 200);
			frame.add(homePoints);
			homeP = Integer.toString(hp);
			homePoints = new JLabel(homeP);
			homePoints.setFont(new Font("Serif", Font.PLAIN, 40));
			homePoints.setBounds(105, 130, 200, 200);
			frame.add(homePoints);

			// away score
			awayPoints = new JLabel("Away Score");
			awayPoints.setFont(new Font("Serif", Font.PLAIN, 40));
			awayPoints.setBounds(430, 80, 200, 200);
			frame.add(awayPoints);
			awayP = Integer.toString(ap);
			awayPoints = new JLabel(awayP);
			awayPoints.setFont(new Font("Serif", Font.PLAIN, 40));
			awayPoints.setBounds(520, 130, 200, 200);
			frame.add(awayPoints);

			// start timer button
			startTimer.setBounds(273, 140, 100, 30);
			startTimer.addActionListener(new ActionTimer());
			frame.add(startTimer);

			// Set
			se = Integer.toString(sets);
			s = new JLabel("Half: " + se);
			s.setFont(new Font("Serif", Font.PLAIN, 30));
			s.setBounds(288, 120, 200, 200);
			frame.add(s);

			// red cards
			HRC = "0";
			ARC = "0";
			homeRed = new JLabel("Red Cards: " + HRC);
			awayRed = new JLabel("Red Cards: " + ARC);
			homeRed.setFont(new Font("Serif", Font.PLAIN, 20));
			homeRed.setBounds(62, 165, 200, 200);
			awayRed.setFont(new Font("Serif", Font.PLAIN, 20));
			awayRed.setBounds(478, 165, 200, 200);
			frame.add(awayRed);
			frame.add(homeRed);

			// text box
			JLabel c = new JLabel("Enter Command:");
			c.setBounds(150, 280, 140, 20);
			c.setFont(new Font("Serif", Font.PLAIN, 20));
			frame.add(c);
			scoreAdder = new JTextField();
			scoreAdder.setBounds(293, 280, 60, 30);
			frame.add(scoreAdder);
			enterB = new JButton("Submit");
			enterB.setBounds(360, 280, 75, 30);
			enterB.addActionListener(new ActionEnter());
			frame.add(enterB);

		}
	}

	static class ActionVolleyball extends JFrame implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent a) {
			sportChosen[1] = true;
			createFrame();
			frame.setLayout(null);
			sets = 1;
			winningScore = 25;
			winningSet = 2;
			System.out.println("Volleyball Rules:");
			System.out.println("The game is played to 25 points, and best of 3 sets.");
			System.out.println(
					"If the score is tied at 24 points, a team must have two more points than the other to win the set.");
			System.out.println(
					"The game will go into the 3rd set only if the game is tied, the 3rd set will be a game up to 15 points.");
			System.out.println();
			System.out.println("Your commands:                       Function:");
			System.out.println("\"1\"                                Add score to home team");
			System.out.println("\"-1\"                               Take away score from home team");
			System.out.println("\"2\"                                Add score to away team");
			System.out.println("\"-2\"                               Take away score from away team");
			System.out.println(
					"\"(team number)=(number)\"           Set (team number)'s score, where 1 is home team and 2 is away team, to (number)");
			// home score
			homePoints = new JLabel("Home Score");
			homePoints.setFont(new Font("Serif", Font.PLAIN, 40));
			homePoints.setBounds(20, 80, 200, 200);
			frame.add(homePoints);
			homeP = Integer.toString(hp);
			homePoints = new JLabel(homeP);
			homePoints.setFont(new Font("Serif", Font.PLAIN, 40));
			homePoints.setBounds(105, 130, 200, 200);
			frame.add(homePoints);

			// away score
			awayPoints = new JLabel("Away Score");
			awayPoints.setFont(new Font("Serif", Font.PLAIN, 40));
			awayPoints.setBounds(430, 80, 200, 200);
			frame.add(awayPoints);
			awayP = Integer.toString(ap);
			awayPoints = new JLabel(awayP);
			awayPoints.setFont(new Font("Serif", Font.PLAIN, 40));
			awayPoints.setBounds(520, 130, 200, 200);
			frame.add(awayPoints);

			// Set
			homeSets = Integer.toString(hs);
			awaySets = Integer.toString(as);
			s = new JLabel(homeSets + " : " + awaySets);
			s.setFont(new Font("Serif", Font.PLAIN, 40));
			s.setBounds(287, 50, 200, 200);
			frame.add(s);

			// text box
			c = new JLabel("Enter Command:");
			c.setBounds(150, 280, 140, 20);
			c.setFont(new Font("Serif", Font.PLAIN, 20));
			frame.add(c);
			scoreAdder = new JTextField();
			scoreAdder.setBounds(293, 280, 60, 30);
			frame.add(scoreAdder);
			enterB = new JButton("Submit");
			enterB.setBounds(360, 280, 75, 30);
			enterB.addActionListener(new ActionEnter());
			frame.add(enterB);
		}
	}

	static class ActionBasketball extends JFrame implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent a) {
			System.out.println("Basketball Rules:");
			System.out.println("The game consists of four quarters of 10 minutes each.");
			System.out.println(
					"Teams can score one point from a free throw, two from a shot made during play, and three for a shot made from outside the three-point line.");
			System.out.println("The timer is paused for when the ball is not in play.");
			System.out.println(
					"If the score is tied after the regular time, the game goes to overtime, which is 5 minutes.");
			System.out.println("");
			System.out.println("Your commands:                       Function:");
			System.out.println("\"1\"                                Add 1 point to home team");
			System.out.println("\"11\"                               Add 2 points to home team");
			System.out.println("\"111\"                              Add 3 points to home team");
			System.out.println("\"-1\"                               Take away 1 point from home team");
			System.out.println("\"2\"                                Add 1 point to away team");
			System.out.println("\"22\"                               Add 2 points to away team");
			System.out.println("\"222\"                              Add 3 points to away team");
			System.out.println("\"-2\"                               Take away 1 point from away team");
			System.out.println(
					"\"(team number)=(number)\"           Set (team number)'s score, where 1 is home team and 2 is away team, to (number)");
			sportChosen[2] = true;
			createFrame();
			frame.setLayout(null);
			sets = 1;
			max = 10;

			startTimer = new JButton("Start Time");
			if(minutes < 10){
				print = "0";
				print += minutes + ":";
				if(seconds < 10){
					print += "0" + seconds;
				}
				else{
					print += seconds;
				}
			}
			else{
				print = minutes + ":";
				if(seconds < 10){
					print += "0" + seconds;
				}
				else{
					print += seconds;
				}
			}

			// time
			dTime = new JLabel(print);
			dTime.setFont(new Font("Serif", Font.PLAIN, 55));
			dTime.setBounds(260, 5, 200, 200);
			frame.add(dTime);
			// start timer button
			startTimer.setBounds(273, 140, 100, 30);
			startTimer.addActionListener(new ActionTimer());
			frame.add(startTimer);

			// home score
			homePoints = new JLabel("Home Score");
			homePoints.setFont(new Font("Serif", Font.PLAIN, 40));
			homePoints.setBounds(20, 80, 200, 200);
			frame.add(homePoints);
			homeP = Integer.toString(hp);
			homePoints = new JLabel(homeP);
			homePoints.setFont(new Font("Serif", Font.PLAIN, 40));
			homePoints.setBounds(105, 130, 200, 200);
			frame.add(homePoints);

			// away score
			awayPoints = new JLabel("Away Score");
			awayPoints.setFont(new Font("Serif", Font.PLAIN, 40));
			awayPoints.setBounds(430, 80, 200, 200);
			frame.add(awayPoints);
			awayP = Integer.toString(ap);
			awayPoints = new JLabel(awayP);
			awayPoints.setFont(new Font("Serif", Font.PLAIN, 40));
			awayPoints.setBounds(520, 130, 200, 200);
			frame.add(awayPoints);

			// Set
			se = Integer.toString(sets);
			s = new JLabel("Quarter: " + se);
			s.setFont(new Font("Serif", Font.PLAIN, 30));
			s.setBounds(265, 110, 200, 200);
			frame.add(s);

			// text box
			c = new JLabel("Enter Command:");
			c.setBounds(150, 280, 140, 20);
			c.setFont(new Font("Serif", Font.PLAIN, 20));
			frame.add(c);
			scoreAdder = new JTextField();
			scoreAdder.setBounds(293, 280, 60, 30);
			frame.add(scoreAdder);
			enterB = new JButton("Submit");
			enterB.setBounds(360, 280, 75, 30);
			enterB.addActionListener(new ActionEnter());
			frame.add(enterB);
		}

	}

	static class ActionTableTennis extends JFrame implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent a) {
			sportChosen[3] = true;
			winningSet = 3;
			winningScore = 11;
			createFrame();
			frame.setLayout(null);
			sets = 1;
			System.out.println("Table Tennis Rules:");
			System.out.println("The game is played to 11 points, and best of 5 sets.");
			System.out.println(
					"If the score is tied at 10 points, a team must have two more points than the other to win the set.");
			System.out.println();
			System.out.println("Your commands:                       Function:");
			System.out.println("\"1\"                                Add score to home team");
			System.out.println("\"-1\"                               Take away score from home team");
			System.out.println("\"2\"                                Add score to away team");
			System.out.println("\"-2\"                               Take away score from away team");
			System.out.println(
					"\"(team number)=(number)\"           Set (team number)'s score, where 1 is home team and 2 is away team, to (number)");

			// home score
			homePoints = new JLabel("Home Score");
			homePoints.setFont(new Font("Serif", Font.PLAIN, 40));
			homePoints.setBounds(20, 80, 200, 200);
			frame.add(homePoints);
			homeP = Integer.toString(hp);
			homePoints = new JLabel(homeP);
			homePoints.setFont(new Font("Serif", Font.PLAIN, 40));
			homePoints.setBounds(105, 130, 200, 200);
			frame.add(homePoints);

			// away score
			awayPoints = new JLabel("Away Score");
			awayPoints.setFont(new Font("Serif", Font.PLAIN, 40));
			awayPoints.setBounds(430, 80, 200, 200);
			frame.add(awayPoints);
			awayP = Integer.toString(ap);
			awayPoints = new JLabel(awayP);
			awayPoints.setFont(new Font("Serif", Font.PLAIN, 40));
			awayPoints.setBounds(520, 130, 200, 200);
			frame.add(awayPoints);

			// Set
			se = Integer.toString(sets);
			s = new JLabel("Set: " + se);
			s.setFont(new Font("Serif", Font.PLAIN, 30));
			s.setBounds(305, 10, 200, 200);
			frame.add(s);

			// text box
			c = new JLabel("Enter Command:");
			c.setBounds(150, 280, 140, 20);
			c.setFont(new Font("Serif", Font.PLAIN, 20));
			frame.add(c);
			scoreAdder = new JTextField();
			scoreAdder.setBounds(293, 280, 60, 30);
			frame.add(scoreAdder);
			enterB = new JButton("Submit");
			enterB.setBounds(360, 280, 75, 30);
			enterB.addActionListener(new ActionEnter());
			frame.add(enterB);

		}
	}

	static class ActionBadminton extends JFrame implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent a) {
			sportChosen[4] = true;
			winningSet = 2;
			winningScore = 21;
			sets = 1;
			createFrame();
			frame.setLayout(null);
			System.out.println("Badminton Rules:");
			System.out.println("The game is played to 21 points, and best of 3 sets.");
			System.out.println(
					"If the score is tied at 20 points, a team must have two more points than the other to win the set.");
			System.out.println("");
			System.out.println("Your commands:   Function:");
			System.out.println("\"1\"                                Add score to home team");
			System.out.println("\"-1\"                               Take away score from home team");
			System.out.println("\"2\"                                Add score to away team");
			System.out.println("\"-2\"                               Take away score from away team");
			System.out.println(
					"\"(team number)=(number)\"           Set (team number)'s score, where 1 is home team and 2 is away team, to (number)");

			// home score
			homePoints = new JLabel("Home Score");
			homePoints.setFont(new Font("Serif", Font.PLAIN, 40));
			homePoints.setBounds(20, 80, 200, 200);
			frame.add(homePoints);
			homeP = Integer.toString(hp);
			homePoints = new JLabel(homeP);
			homePoints.setFont(new Font("Serif", Font.PLAIN, 40));
			homePoints.setBounds(105, 130, 200, 200);
			frame.add(homePoints);

			// away score
			awayPoints = new JLabel("Away Score");
			awayPoints.setFont(new Font("Serif", Font.PLAIN, 40));
			awayPoints.setBounds(430, 80, 200, 200);
			frame.add(awayPoints);
			awayP = Integer.toString(ap);
			awayPoints = new JLabel(awayP);
			awayPoints.setFont(new Font("Serif", Font.PLAIN, 40));
			awayPoints.setBounds(520, 130, 200, 200);
			frame.add(awayPoints);

			// Set
			se = Integer.toString(sets);
			s = new JLabel("Set: " + se);
			s.setFont(new Font("Serif", Font.PLAIN, 30));
			s.setBounds(305, 10, 200, 200);
			frame.add(s);

			// text box
			JLabel c = new JLabel("Enter Command:");
			c.setBounds(150, 280, 140, 20);
			c.setFont(new Font("Serif", Font.PLAIN, 20));
			frame.add(c);
			scoreAdder = new JTextField();
			scoreAdder.setBounds(293, 280, 60, 30);
			frame.add(scoreAdder);
			enterB = new JButton("Submit");
			enterB.setBounds(360, 280, 75, 30);
			enterB.addActionListener(new ActionEnter());
			frame.add(enterB);
		}
	}

	static class ActionCustom implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			createFrame();
			frame.setLayout(null);
			System.out.println("Please input the amount of minutes you want in the textbox at the top.");
			System.out.println("Your commands:                       Function:");
			System.out.println("\"1\"                                Add score to home team");
			System.out.println("\"-1\"                               Take away score from home team");
			System.out.println("\"2\"                                Add score to away team");
			System.out.println("\"-2\"                               Take away score from away team");
			System.out.println(
					"\"(team number)=(number)\"           Set (team number)'s score, where 1 is home team and 2 is away team, to (number)");

			// home score
			homePoints = new JLabel("Home Score");
			homePoints.setFont(new Font("Serif", Font.PLAIN, 40));
			homePoints.setBounds(20, 80, 200, 200);
			frame.add(homePoints);
			homeP = Integer.toString(hp);
			homePoints = new JLabel(homeP);
			homePoints.setFont(new Font("Serif", Font.PLAIN, 40));
			homePoints.setBounds(105, 130, 200, 200);
			frame.add(homePoints);

			// away score
			awayPoints = new JLabel("Away Score");
			awayPoints.setFont(new Font("Serif", Font.PLAIN, 40));
			awayPoints.setBounds(430, 80, 200, 200);
			frame.add(awayPoints);
			awayP = Integer.toString(ap);
			awayPoints = new JLabel(awayP);
			awayPoints.setFont(new Font("Serif", Font.PLAIN, 40));
			awayPoints.setBounds(520, 130, 200, 200);
			frame.add(awayPoints);

			// text box
			JLabel c = new JLabel("Enter Command:");
			c.setBounds(150, 280, 140, 20);
			c.setFont(new Font("Serif", Font.PLAIN, 20));
			frame.add(c);
			scoreAdder = new JTextField();
			scoreAdder.setBounds(293, 280, 60, 30);
			frame.add(scoreAdder);
			enterB = new JButton("Submit");
			enterB.setBounds(360, 280, 75, 30);
			enterB.addActionListener(new ActionEnter());
			frame.add(enterB);

			// timer text box
			JLabel timeLabel = new JLabel("Enter time in minutes:");
			timeLabel.setFont(new Font("Serif", Font.PLAIN, 20));
			timeLabel.setBounds(240, 0, 500, 200);
			frame.add(timeLabel);
			JTextField cTime = new JTextField();
			cTime.setBounds(250, 125, 60, 30);
			frame.add(cTime);
			JButton enterB2 = new JButton("Submit");
			enterB2.setBounds(320, 125, 80, 30);
			enterB2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					inputTime = cTime.getText();
					try{
						inTime = Integer.valueOf(inputTime);
					}
					catch(Exception er){
						System.out.println("Input a time please");
					}
					cTime.setText("");
					enterB2.setOpaque(false);
					enterB2.setContentAreaFilled(false);
					enterB2.setBorderPainted(false);
					enterB2.setText("");
					cTime.setBounds(1000, 1000, 0, 0);
					timeLabel.setText("");
					max = inTime;
					startTimer = new JButton("Start Time");
					if(minutes < 10){
						print = "0";
						print += minutes + ":";
						if(seconds < 10){
							print += "0" + seconds;
						}
						else{
							print += seconds;
						}
					}
					else{
						print = minutes + ":";
						if(seconds < 10){
							print += "0" + seconds;
						}
						else{
							print += seconds;
						}
					}
					// time
					dTime = new JLabel(print);
					dTime.setFont(new Font("Serif", Font.PLAIN, 55));
					dTime.setBounds(260, 5, 200, 200);
					frame.add(dTime);

					// start timer button
					startTimer.setBounds(273, 140, 100, 30);
					startTimer.addActionListener(new ActionTimer());
					frame.add(startTimer);
				}
			});
			frame.add(enterB2);
		}
	}
}