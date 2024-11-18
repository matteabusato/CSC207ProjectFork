import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Insurance {
    private JFrame frame;
    private JPanel mainPanel;
    private JComboBox<String> insuranceTypeComboBox;
    private JTextArea policyDetailsTextArea;
    private JButton viewPolicyButton, fileClaimButton, updatePolicyButton, checkAvailabilityButton, purchaseInsuranceButton;

    public Insurance() {
        initializeUI();
    }

    private void initializeUI() {
        // Setting up the frame
        frame = new JFrame("Insurance Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLayout(new BorderLayout());

        // Creating the main panel
        mainPanel = new JPanel(new GridLayout(8, 2, 10, 10));

        // Insurance Type ComboBox
        insuranceTypeComboBox = new JComboBox<>(new String[]{
                "Select Insurance Type",
                "Health Insurance",
                "Life Insurance",
                "Vehicle Insurance",
                "Home Insurance",
                "Travel Insurance",
                "Business Insurance"
        });
        mainPanel.add(new JLabel("Insurance Type:"));
        mainPanel.add(insuranceTypeComboBox);

        // Policy Details Text Area
        policyDetailsTextArea = new JTextArea(10, 40);
        policyDetailsTextArea.setLineWrap(true);
        policyDetailsTextArea.setWrapStyleWord(true);
        policyDetailsTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(policyDetailsTextArea);
        mainPanel.add(new JLabel("Policy Details:"));
        mainPanel.add(scrollPane);

        // View Policy Button
        viewPolicyButton = new JButton("View Policy Details");
        viewPolicyButton.addActionListener(e -> viewPolicyDetails());
        mainPanel.add(viewPolicyButton);

        // Renew Policy Button


        // File Claim Button
        fileClaimButton = new JButton("File a Claim");
        fileClaimButton.addActionListener(e -> fileClaim());
        mainPanel.add(fileClaimButton);

        // Update Policy Button
        updatePolicyButton = new JButton("Update Policy");
        updatePolicyButton.addActionListener(e -> updatePolicy());
        mainPanel.add(updatePolicyButton);

        // Check Availability Button
        checkAvailabilityButton = new JButton("Check Availability");
        checkAvailabilityButton.addActionListener(e -> checkAvailability());
        mainPanel.add(checkAvailabilityButton);

        // Purchase Insurance Button
        purchaseInsuranceButton = new JButton("Purchase Insurance");
        purchaseInsuranceButton.addActionListener(e -> purchaseInsurance());
        mainPanel.add(purchaseInsuranceButton);

        // Adding main panel to frame
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void viewPolicyDetails() {
        String selectedInsurance = (String) insuranceTypeComboBox.getSelectedItem();
        String policyDetails = "";

        switch (selectedInsurance) {
            case "Health Insurance":
                policyDetails = "Health Insurance: Covers hospitalization, surgeries, and critical illnesses.\nSum Insured: $200,000\nPremium: $500/year";
                break;
            case "Life Insurance":
                policyDetails = "Life Insurance: Provides financial protection to your family in case of your demise.\nCoverage: Term Life, Whole Life\nPremium: Varies based on coverage amount.";
                break;
            case "Vehicle Insurance":
                policyDetails = "Vehicle Insurance: Covers car/motorcycle damage, third-party liability, and theft.\nPremium: Varies based on vehicle model and age.";
                break;
            case "Home Insurance":
                policyDetails = "Home Insurance: Covers property damage due to fire, theft, or natural disasters.\nPremium: Varies based on property value.";
                break;
            case "Travel Insurance":
                policyDetails = "Travel Insurance: Covers medical emergencies, trip cancellation, and lost luggage during travel.\nPremium: Depends on trip duration and destination.";
                break;
            case "Business Insurance":
                policyDetails = "Business Insurance: Covers liability, property damage, and employee risks.\nPremium: Varies based on business type and risk factors.";
                break;
            default:
                policyDetails = "Please select a valid insurance type to view details.";
                break;
        }
        policyDetailsTextArea.setText(policyDetails);
    }



    private void fileClaim() {
        String selectedInsurance = (String) insuranceTypeComboBox.getSelectedItem();
        if (selectedInsurance.equals("Select Insurance Type")) {
            JOptionPane.showMessageDialog(frame, "Please select an insurance type before filing a claim.");
        } else {
            String claimReason = JOptionPane.showInputDialog(frame, "Enter the reason for your claim:");
            if (claimReason != null && !claimReason.trim().isEmpty()) {
                int decision = (int) (Math.random() * 2); // 0 for declined, 1 for accepted
                if (decision == 1) {
                    JOptionPane.showMessageDialog(frame, "Your claim has been accepted.");
                } else {
                    JOptionPane.showMessageDialog(frame, "Your claim has been declined. Please contact support for more details.");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Claim reason cannot be empty.");
            }
        }
    }

    private void updatePolicy() {
        String selectedInsurance = (String) insuranceTypeComboBox.getSelectedItem();
        if (selectedInsurance == null || selectedInsurance.equals("Select Insurance Type")) {
            JOptionPane.showMessageDialog(frame, "Please select an insurance type to update the policy.");
        } else {
            String updateDetails = JOptionPane.showInputDialog(frame, "Enter the new details to update the policy:");
            if (updateDetails != null && !updateDetails.trim().isEmpty()) {
                policyDetailsTextArea.setText(updateDetails); // Update policy details text area to reflect changes
                JOptionPane.showMessageDialog(frame, "Policy updated successfully with the following details:\n" + updateDetails);
            } else {
                JOptionPane.showMessageDialog(frame, "Update details cannot be empty.");
            }
        }
    }

    private void checkAvailability() {
        String selectedInsurance = (String) insuranceTypeComboBox.getSelectedItem();
        if (selectedInsurance.equals("Select Insurance Type")) {
            JOptionPane.showMessageDialog(frame, "Please select an insurance type to check availability.");
        } else {
            String profileInfo = JOptionPane.showInputDialog(frame, "Enter your profile information (age, location, occupation):");
            if (profileInfo != null && !profileInfo.trim().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Based on your profile information, the selected insurance is available.");
            } else {
                JOptionPane.showMessageDialog(frame, "Profile information cannot be empty.");
            }
        }
    }

    private void purchaseInsurance() {
        String selectedInsurance = (String) insuranceTypeComboBox.getSelectedItem();
        if (selectedInsurance.equals("Select Insurance Type")) {
            JOptionPane.showMessageDialog(frame, "Please select an insurance type before purchasing.");
        } else {
            int confirmation = JOptionPane.showConfirmDialog(frame, "Are you sure you want to purchase " + selectedInsurance + "?", "Confirm Purchase", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(frame, "Insurance purchased successfully!");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Insurance::new);
    }
}
