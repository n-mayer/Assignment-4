import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Random;

/* ASSUMPTIONS:
 *
 * 1. A Tower is a human (I have never heard this term in this context)
 * 2. Code that lets clients send info is already in place; this is simulated locally with classes Client & Data
 * 3. Charged client records are moved somewhere (a separate file) for safe storage
*/
interface FrameModel {

    public JFrame getFrame();
    public void reveal(FrameModel fm);
    public void hide();
}

//Generates client name, ID, phone #, & point A -> B. Stored in class Data
class Client {

    private Random rand = new Random();

    private String firsts[] = {"Stefan", "Lyle", "Jake", "Bryce", "Dennis", "Moshe", "Keegan", "Colt", "Harrison", "Matias", "Wyatt", "Jaxx", "Kristopher", "Lukas", "Kelvin", "Nasir", "Waylon", "Giovanni", "Benjamin", "Bowen", "Dax", "Branson", "Alvin", "Albert", " Canaan", "Haisley", "Myah", " Charley", " Clara", " Cara", " Annalee", " Antonella", " Lana", " Janessa", " Lara", " Madisyn", " Adelyn", " Bianca", " Irene", " Dayana", " Keily", " Danielle", " Roselyn", " Alyson", " Melina", " Joy", " Lilyana", " Sylvie", " Carmen", " Alani"};
    private String lasts[] = {"Macdonald", "Giles", "Frey", "Lawrence", "White", "West", "Waller", "Navarro", "Mckinney", "Marshall", "Steele", "Whitaker","Casey", "Mitchell", "Patterson", "Holt","Shepherd", "Decker", "Gordon", "Pace", "Chapman", "Dunn", "Mcbride", "Kirby", "Young", "Alvarez", "Pratt", "Riley", "Marquez", "Blankenship", "Sparks", "Bates", "Hale", "Middleton", "Thornton", "Daugherty", "Villarreal", "Wood", "Rollins", "Rowe", "Mcfarland", "Francis", "Gonzales", "Barrett", "Arias", "Key", "Bright", "Rose", "Pennington", "Miles"};
    private String streets[] = {"Delaware Avenue", "Cooper Street", "Orchard Street", "Lexington Drive", "Heritage Drive", "King Street", "Route 9", "College Street", "Ridge Road", "Spruce Street", "Morris Street", "Walnut Street", "Cherry Lane", "Augusta Drive", "Academy Street", "Jackson Avenue", "Lake Street", "Main Street South", "Route 202", "Pearl Street", "Spruce Avenue", "Cedar Court", "Fairview Avenue", "Briarwood Drive", "Poplar Street", "Pheasant Run", "Oxford Road", "4th Street South", "Franklin Court", "Cleveland Avenue", "Vine Street", "Aspen Court", "Canterbury Drive", "Market Street", "Broad Street West", "Fairview Road", "Chestnut Street", "Lantern Lane", "Route 44", "Maple Avenue", "8th Avenue", "Beechwood Drive", "Hickory Lane", "Main Street East", "1st Street", "Essex Court", "Jefferson Street", "Monroe Drive", "Lilac Lane", "Colonial Drive"};

    private String firstName;
    private String lastName;
    private String clientID;
    private String phone;
    private String from;
    private String to;
    
    public Client() {

        from = "";
        to = "";

        setFirst();
        setLast();
        setPhone();
        setTo();
        setFrom();
        setClientID();
    }

    public String getFirst() { return firstName; }
    public void setFirst() {
        firstName = firsts[rand.nextInt(firsts.length)];
    }

    public String getLast() { return lastName; }
    public void setLast() {
        lastName = lasts[rand.nextInt(lasts.length)];
    }

    public String getPhone() { return phone; }
    public void setPhone() {

        phone = "(305) ";

        for(int i = 0; i < 7; i++) {
            phone += rand.nextInt(10);
            if(i == 2) phone += "-";
        }
    }

    public String getTo() { return to; }
    public String getFrom() { return from; }
    public void setTo() { to = streets[rand.nextInt(streets.length)]; }
    public void setFrom() { from = streets[rand.nextInt(streets.length)]; }

    //ID is displayed as a string of letters and digits
    public String getClientID() {
        return clientID;
    }

    public void setClientID() {

        clientID = "";

        for(int i = 0; i < 7; i++) {

            int coin = rand.nextInt(2);

            if(coin == 0) clientID += rand.nextInt(10);
            else clientID += (char) (rand.nextInt(26) + 'A');
        }
    }
}

class Data {

    private DataModel dm = new DataModel(this);
    private Client clients[] = new Client[25];
    private Client currClient;
    private ArrayList<Client> chargedClients = new ArrayList<>();

    public Data() {
        for(int i = 0; i < clients.length; i++) clients[i] = new Client();
    }

    public Client[] getClients() {
        return clients;
    }

    public Client getClient() {
        return currClient;
    }

    public void clientCharged(Client c) {
        chargedClients.add(c);
    }

    public DataModel getDataModel() {
        return dm;
    }
}

class ClientInfoUI implements FrameModel {

    private Client client;

    private JFrame clientFrame;
    private JPanel infoPanel = new JPanel();
    private JPanel ratePanel = new JPanel();

    private JLabel rateLabel = new JLabel("Rate: $");
    private JLabel jobTypeLabel = new JLabel("Job:");

    private JTextField jobTypeField = new JTextField();
    private JSpinner rate = new JSpinner(new SpinnerNumberModel(40, 40, 200, 1));

    private JButton okButton = new JButton("OK");
    private JButton cancelButton = new JButton("Cancel");

    public ClientInfoUI(Client c, Data d) {

        client = c;

        clientFrame = new JFrame(c.getClientID());

        clientFrame.setResizable(false);
        clientFrame.setLayout(new GridLayout(2, 1, 0, 0));
        clientFrame.setSize(350, 200);
        clientFrame.setLocationRelativeTo(null);
        clientFrame.setName(c.getClientID().toString());

        clientFrame.add(infoPanel);
        clientFrame.add(ratePanel);

        infoPanel.setLayout(new GridLayout(2, 2, 0, 0));

        infoPanel.add(new JLabel("      Name: " + c.getFirst() + " " + c.getLast()));
        infoPanel.add(new JLabel("  To: " + c.getTo()));
        infoPanel.add(new JLabel("      Phone #: " + c.getPhone()));
        infoPanel.add(new JLabel("  From: " + c.getFrom()));

        ratePanel.setLayout(null);
        ratePanel.add(rateLabel);
        ratePanel.add(rate);
        ratePanel.add(jobTypeLabel);
        ratePanel.add(jobTypeField);
        ratePanel.add(okButton);
        ratePanel.add(cancelButton);

        rateLabel.setSize(50, 25);
        rateLabel.setLocation(19, 10);

        rate.setSize(50, 25);
        rate.setLocation(60, rateLabel.getY());

        jobTypeLabel.setSize(100, 25);
        jobTypeLabel.setLocation(rate.getX() + 100, rateLabel.getY());

        jobTypeField.setSize(115, 25);
        jobTypeField.setLocation(jobTypeLabel.getX() + 30, rateLabel.getY());

        okButton.setSize(80, 27);
        okButton.setLocation(rateLabel.getX(), 50);

        cancelButton.setSize(80, okButton.getHeight());
        cancelButton.setLocation(225, okButton.getY());

        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                d.clientCharged(c);
                reveal(d.getDataModel());
                clientFrame.dispose();
            }
        });
        
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clientFrame.dispose();
            }
        });
    }

    public JFrame getFrame() { return clientFrame; }

    public Client getClient() {
        return client;
    }

    public void reveal(FrameModel fm) { fm.getFrame().setVisible(true); }
    public void hide() { this.getFrame().setVisible(false); }
}

class ClientIDs implements FrameModel {

    private JFrame list = new JFrame();
    private JPanel idPanel = new JPanel(new GridLayout(50, 1, 0, 0));
    private JPanel chargePanel = new JPanel(new GridLayout(7, 1, 0, 0));

    private JTextField idTextField = new JTextField();

    private JButton chargeClientButton = new JButton("Charge");

    public ClientIDs(Data data) {

        list.setResizable(false);
        list.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        list.setLayout(new GridLayout(1, 2));
        list.setSize(300, 250);
        list.setLocationRelativeTo(null);

        list.add(new JScrollPane(idPanel));
        list.add(chargePanel);

        chargePanel.add(chargeClientButton);
        chargePanel.add(idTextField);

        idTextField.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));

        chargeClientButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                for(Component l : idPanel.getComponents()) {

                    String text = ((JLabel)l).getText();

                    // IF jlabel text = text field text
                    if(text.equals(idTextField.getText().trim())) {

                        for(Client c : data.getClients()) {

                            // IF client id = text field text
                            if(c.getClientID().equals(idTextField.getText().trim())) {

                                //hide();
                                ClientInfoUI cui = new ClientInfoUI(c, data);
                                cui.reveal(cui);
                            }
                        }
                    }
                }
            }
        });
        fillClientPanel(data, idPanel);
        idTextField.setText(((JLabel)idPanel.getComponent(1)).getText());
    }

    public void fillClientPanel(Data data, JPanel jp) {

        for(Client c : data.getClients()) {

            JLabel label = new JLabel(c.getClientID());
            label.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
            jp.add(label);
        }
    }

    public JFrame getFrame() { return list; }

    public void reveal(FrameModel fm) { fm.getFrame().setVisible(true); }
    public void hide() { this.getFrame().setVisible(false); }
}

class CredentialsGUI implements FrameModel {

    // JFrame where Tower enters username & password
    ClientIDs ids;

    private String username = "";
    private String password = "";

    private JFrame credentials = new JFrame();

    JPanel credPanel = new JPanel();

    private JLabel userLabel = new JLabel("Username:");
    private JLabel passLabel = new JLabel("Password:");
    private JLabel badCredentials = new JLabel("Incorrect username or password");

    private JButton enter = new JButton("Continue");

    private JTextField userField = new JTextField();
    private JTextField passField = new JTextField();

    private String getUserField() { return userField.getText(); }
    private String getPassField() { return passField.getText(); }

    // builds layout/appearance
    public CredentialsGUI(Data d) {
        
        //credentials
        credentials.setSize(250, 220);
        credentials.setLocationRelativeTo(null);
        credentials.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        credentials.add(credPanel, BorderLayout.CENTER);
        
        //credPanel
        credPanel.setLayout(null);
        credPanel.add(badCredentials);
        credPanel.add(userField);
        credPanel.add(passField);
        credPanel.add(userLabel);
        credPanel.add(passLabel);
        credPanel.add(enter);

        //tries username/password check
        enter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkCredentials(getUserField(), getPassField(), d);
            }
        });

        badCredentials.setForeground(Color.RED);

        //text field & button bounds
        userField.setBounds(40, 30, 100, 25);
        passField.setBounds(userField.getX(), userField.getY() + 50, 100, 25);
        enter.setBounds    (userField.getX(), userField.getY() + 85, 85, 25 );
        
        //label bounds
        userLabel.setBounds(userField.getX(), userField.getY() - 20, 100, 25);
        passLabel.setBounds(passField.getX(), passField.getY() - 20, 100, 25);
        badCredentials.setBounds(20, 140, 250, 25);

        //final bits
        credentials.setResizable(false);
        badCredentials.setVisible(false);
    }

    public void checkCredentials(String user, String pass, Data d) {

        if(user.equals(username) && pass.equals(password)) {
            reveal(new ClientIDs(d));
            hide();
        }
        else badCredentials.setVisible(true);
    }
    
    public JFrame getFrame() { return credentials; }

    public void reveal(FrameModel fm) { fm.getFrame().setVisible(true); }
    public void hide() { this.getFrame().setVisible(false); }
}

class DataModel implements FrameModel {

    private int requests = 0;

    private JFrame dataFrame = new JFrame("Records Updated");
    private TableModel dataModel = new AbstractTableModel() {
        
        public int getColumnCount() { return 10; }
        public int getRowCount() {return 7; }
        public Object getValueAt(int row, int col) { return (row * col); }
    };

    private JTable dataTable = new JTable(dataModel);
    JScrollPane sp = new JScrollPane(dataTable);

    public DataModel(Data d) {

        dataFrame.setSize(300, 200);
        dataFrame.setLayout(new GridLayout(2, 1, 0, 0));
        dataFrame.setResizable(false);
        dataFrame.setLocationRelativeTo(null);

        dataFrame.add(sp);
    }

    @Override
    public JFrame getFrame() {
        return dataFrame;
    }

    @Override
    public void reveal(FrameModel fm) {
        fm.getFrame().setVisible(true);
    }

    @Override
    public void hide() {
        dataFrame.setVisible(false);
    }
    
}
//contains main
public class Template {

    public static void main(String[] args) {

        Data data = new Data();
        CredentialsGUI credentials = new CredentialsGUI(data);

        credentials.reveal(credentials);
    }
}