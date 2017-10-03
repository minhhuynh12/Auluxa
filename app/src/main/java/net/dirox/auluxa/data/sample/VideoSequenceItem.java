package net.dirox.auluxa.data.sample;

import net.dirox.auluxa.R;

/**
 * Created by an on 6/30/2017.
 */

public class VideoSequenceItem {
    public String sequenceName;
    public int selectedButtonId;
    public String buttonText;


    public VideoSequenceItem(int sequenceIndex, int buttonId) {
        this.sequenceName = "SEQUENCE " + String.valueOf(sequenceIndex + 1);
        this.selectedButtonId = buttonId;
        this.buttonText = getButtonSequenceText(buttonId);
    }

    private String getButtonSequenceText(int buttonId) {
        switch (buttonId) {
            case R.id.llMute:
                return "Mute";
            case R.id.llHome:
                return "Home";
            case R.id.llPower:
                return "Power on";
            case R.id.llBack:
                return "Back";
            case R.id.llMenu:
                return "Menu";
            case R.id.llApps:
                return "App";
            case R.id.llExit:
                return "Exit";
            case R.id.llRed:
                return "Red";
            case R.id.llGreen:
                return "Green";
            case R.id.llYellow:
                return "Yellow";
            case R.id.llBlue:
                return "Blue";

            case R.id.llCustom1:
                return "Custom 1";
            case R.id.llCustom2:
                return "Custom 2";
            case R.id.llCustom3:
                return "Custom 3";
            case R.id.llCustom4:
                return "Custom 4";
            case R.id.llCustom5:
                return "Custom 5";
            case R.id.llCustom6:
                return "Custom 6";
            case R.id.llOne:
                return "CH 1";
            case R.id.llTwo:
                return "CH 2";
            case R.id.llThree:
                return "CH 3";
            case R.id.llFour:
                return "CH 4";
            case R.id.llFive:
                return "CH 5";
            case R.id.llSix:
                return "CH 6";
            case R.id.llSeven:
                return "CH 7";
            case R.id.llEight:
                return "CH 8";
            case R.id.llNine:
                return "CH 9";
            case R.id.llZero:
                return "CH 0";
            case R.id.llCharacter:
                return "-/--";
            case R.id.llClear:
                return "Clear";

            case R.id.llSource:
                return "Source";

            case R.id.llPlay:
                return "Play";
            case R.id.llPause:
                return "Pause";
            case R.id.llStop:
                return "Stop";
            case R.id.llNext:
                return "Next";
            case R.id.llPrevious:
                return "Previous";

            case R.id.llUp:
                return "Eject";

            case R.id.lnSleep:
                return "Sleep";

            case R.id.lnCustom1:
                return "Custom 1";
            case R.id.lnCustom2:
                return "Custom 2";
            case R.id.lnCustom3:
                return "Custom 3";
            case R.id.lnCustom4:
                return "Custom 4";
            case R.id.lnCustom5:
                return "Custom 5";
            case R.id.lnCustom6:
                return "Custom 6";
            case R.id.lnCustom7:
                return "Custom 7";
            case R.id.lnCustom8:
                return "Custom 8";
            case R.id.lnCustom9:
                return "Custom 9";

            default:
                return "EMPTY";
        }
    }
}
