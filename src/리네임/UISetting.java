/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package 리네임;

import de.javasoft.plaf.synthetica.*;

import java.text.ParseException;

import javax.swing.*;

/**
 *
 * @author Administrator
 */
public class UISetting {
	JFrame theFrame;

	{
		UIInstel();

	}

	UISetting(JFrame theFrame) {
		this.theFrame = theFrame;
		UISelect(5);
	}

	UISetting(JFrame theFrame, int styleNum) {
		this.theFrame = theFrame;
		UISelect(styleNum);
	}

	UISetting(JFrame theFrame, String StyleName) {
		this.theFrame = theFrame;
		UISelet(StyleName);
	}

	void UIInstel() {
		UIManager.put("Synthetica.window.decoration", Boolean.FALSE);

	}

	void UISelect(int styleNum) {
		try {
			switch (styleNum) {
			case 0:
				UISelet("Metal");
				break;
			case 1:
				UISelet("Nimbus");
				break;
			case 2:
				UISelet("CDE/Motif");
				break;
			case 3:
				UISelet("Windows");
				break;
			case 4:
				UISelet("Windows Classic");
				break;
			case 5:
				UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());
				break;
			case 6:
				UIManager.setLookAndFeel(new SyntheticaAluOxideLookAndFeel());
				break;
			case 7:
				UIManager.setLookAndFeel(new SyntheticaOrangeMetallicLookAndFeel());
				break;
			case 8:
				UIManager.setLookAndFeel(new SyntheticaMauveMetallicLookAndFeel());
				break;
			case 9:
				UIManager.setLookAndFeel(new SyntheticaBlueSteelLookAndFeel());
				break;
			case 10:
				UIManager.setLookAndFeel(new SyntheticaBlueMoonLookAndFeel());
				break;
			case 11:
				UIManager.setLookAndFeel(new SyntheticaBlackMoonLookAndFeel());
				break;
			default:
				break;
			}

		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UIupdate();
	}

	void UISelet(String StyleName) {

		switch (StyleName) {
		case "BlackEye":
			UISelect(5);
			break;
		case "AluOxide":
			UISelect(6);
			break;
		case "OrangeMetallic":
			UISelect(7);
			break;
		case "MauveMetallic":
			UISelect(8);
			break;
		case "BlueSteel":
			UISelect(9);
			break;
		case "BlueMoon":
			UISelect(10);
			break;
		case "BlackMoon":
			UISelect(11);
			break;
		default:
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				System.out.println(info.getName() + ":tf:" + StyleName.equals(info.getName()));
				// System.out.println();
				if (StyleName.equals(info.getName())) {
					try {
						UIManager.setLookAndFeel(info.getClassName());
						UIupdate();
						return;
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (UnsupportedLookAndFeelException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			System.out.println("해당 스타일이 없습니다.");
			break;
		}
	}

	void UIupdate() {
		SyntheticaLookAndFeel.setFont("Dialog", 12);
		SwingUtilities.updateComponentTreeUI(theFrame);
	}
}
