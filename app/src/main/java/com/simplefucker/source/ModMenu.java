package com.simplefucker.source;

import android.animation.ArgbEvaluator;
import android.animation.TimeAnimator;
import android.view.animation.LinearInterpolator;
import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.text.Html;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.util.Base64;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.ref.WeakReference;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.widget.RelativeLayout.ALIGN_PARENT_RIGHT;
import static android.widget.RelativeLayout.ALIGN_PARENT_TOP;
import static android.widget.RelativeLayout.CENTER_HORIZONTAL;
import static android.widget.RelativeLayout.CENTER_IN_PARENT;
import android.widget.HorizontalScrollView;

public class ModMenu extends Service {
    //********** Here you can easly change the menu appearance **********//
    int TEXT_COLOR = Color.parseColor("#FFFFFF");
    int TEXT_COLOR_2 = Color.parseColor("#FFFFFF");
    int MENU_STROKEa = 255;
    int MENU_STROKEr = 255;
    int MENU_STROKEg = 0;
    int MENU_STROKEb = 0;
    GradientDrawable MENU_STROKE = new GradientDrawable();
    int MENU_STROKE_GRADIENT = Color.argb(MENU_STROKEa - 150,MENU_STROKEr,MENU_STROKEg,MENU_STROKEb);
    int MENU_STROKE_COLOR = Color.argb(MENU_STROKEa,MENU_STROKEr,MENU_STROKEg,MENU_STROKEb);
    int BTN_COLOR = Color.parseColor("#1C191F");
    int MENU_BG_COLOR = Color.parseColor("#ff322F42"); //#AARRGGBB
    int MENU_FEATURE_BG_COLOR = Color.parseColor("#ff1F1C22"); //#AARRGGBB
    GradientDrawable MENU_FEATURE_BG_COLOR_GRADIENT = new GradientDrawable();
    int NOCOLOR = Color.parseColor("#00000000"); //#AARRGGBB
    int MENU_WIDTH = 700;
    int MENU_HEIGHT = 400;
    float MENU_CORNER = 4f;
    int ICON_SIZE = 50; //Change both width and height of image
    float ICON_ALPHA = 0.7f; //Transparent

    int HintTxtColor = Color.parseColor("#FF171E24");
    int I = Color.parseColor("#5f72be");
    int II = Color.parseColor("#9921e8");
    int red = 0,green = 0,blue = 0,redi = 0,bluei = 0,greeni = 0;
    int ToggleON = Color.parseColor("#138dc2");
    int ToggleOFF = Color.parseColor("#aeaeae");

    int BtnON = Color.parseColor("#322F42");
    int BtnOFF = Color.parseColor("#1C191F");
    int CategoryBG =  Color.parseColor("#391e1f21");
    int SeekBarColor = Color.parseColor("#80CBC4");
    int SeekBarProgressColor = Color.parseColor("#80CBC4");
    int CheckBoxColor = Color.parseColor("#80CBC4");
    int RadioColor =  Color.parseColor("#FFFFFF");
    String fetched = null;
    String NumberTxt = "#41c300";
    //********************************************************************//


    GradientDrawable gdMenuBody,gdMenuBody1, gdAnimation = new GradientDrawable();
    RelativeLayout mCollapsed, mRootContainer, layout,titleText1;
    LinearLayout mExpanded, patches, mSettings, Login;
    LinearLayout.LayoutParams scrlLLExpanded, scrlLL;
    WindowManager mWindowManager;
    WindowManager.LayoutParams params;
    ImageView startimage;
    FrameLayout rootFrame;
    AlertDialog alert;
    EditText edittextvalue;
    ScrollView scrollView;
    LinearLayout titleText;
    //For alert dialog
    TextView inputFieldTextView,title;
    String inputFieldFeatureName;
    int inputFieldFeatureNum;
    EditTextValue inputFieldTxtValue;

    boolean stopChecking, settingsOpen;

    //initialize methods from the native library
    native String Title();

    native String Heading();

    native String Icon();

    native String IconWebViewData();

    native String[] getFeatureList();
    native String[] getSettings();
    native String[] getPlayer();

    native String[] getVisuals();

    native String[] getWeapon();

    native String[] getMisc();

    native String[] settingsList();

    native String[] loginList();

    native boolean isGameLibLoaded();

    GradientDrawable gdMenuStroke = new GradientDrawable();

    GradientDrawable fuckedcumedpoloska;

    GradientDrawable gdMenuStroke1 = new GradientDrawable();
    //When this Class is called the code in this function will be executed
    @Override
    public void onCreate() {

        super.onCreate();
        SavedPrefs.context = this;

        //Create the menu
        initFloating();
        initAlertDiag();


        //Create a handler for this Class
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            public void run() {
                Thread();
                handler.postDelayed(this, 1000);
            }
        });
    }
    public enum Category {
        hacks ,  visuals ,  extra , other ,
    }


    private void SwitchMenu(Category category){
        patches.removeAllViews();
        /*****************************************/
        if (category == Category. hacks ) {
            addlinears(getPlayer(),patches);
        }
        /*****************************************/
        if (category == Category. visuals ) {
            addlinears(getVisuals(),patches);
        }
        /*****************************************/
        if (category == Category. extra ) {
            addlinears(getMisc(),patches);
        }
        /*****************************************/
        if (category == Category. other ) {
            addlinears(getWeapon(),patches);
        }
        /*****************************************/
    }
    private void initFloating() {
        red = 0;
        blue = 0;
        green = 0;
        redi = 0;
        bluei = 0;
        greeni = 0;
        rootFrame = new FrameLayout(this); // Global markup
        rootFrame.setOnTouchListener(onTouchListener());
        mRootContainer = new RelativeLayout(this); // Markup on which two markups of the icon and the menu itself will be placed
        mCollapsed = new RelativeLayout(this); // Markup of the icon (when the menu is minimized)
        mCollapsed.setVisibility(View.VISIBLE);
        mCollapsed.setAlpha(ICON_ALPHA);
        title = new TextView(this);
        title.setPadding(20, 0, 20, 0);
        byte[] fucker = Base64.decode(Title(),0);
        title.setText(new String(fucker));
        title.setTextColor(TEXT_COLOR);
        title.setTypeface(Typeface.DEFAULT_BOLD);
        title.setTextSize(18.0f);
        title.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                patches.removeAllViews();
                addlinears(getSettings(),patches);
            }
        });
        RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        title.setLayoutParams(rl);
        //********** The box of the mod menu **********
        mExpanded = new LinearLayout(this); // Menu markup (when the menu is expanded)
        mExpanded.setVisibility(View.GONE);
        mExpanded.setGravity(Gravity.CENTER);
        mExpanded.setOrientation(LinearLayout.VERTICAL);
        mExpanded.setLayoutParams(new LinearLayout.LayoutParams(convertDipToPixels(400), convertDipToPixels(300)));
        gdMenuBody = new GradientDrawable();
        gdMenuBody.setCornerRadius(MENU_CORNER); //Set corner
        gdMenuBody.setColor(MENU_BG_COLOR); //Set background color

        gdMenuBody1 = new GradientDrawable();
        gdMenuBody1.setCornerRadius(MENU_CORNER); //Set corner
        gdMenuBody1.setColor(MENU_BG_COLOR); //Set background color
        MENU_FEATURE_BG_COLOR_GRADIENT.setColor(Color.parseColor("#ff1F1C22"));
        MENU_FEATURE_BG_COLOR_GRADIENT.setCornerRadius(10f);
        int[] gradient = {I,II};
        MENU_STROKE.setCornerRadius(10f);
        gdMenuStroke.setColor(MENU_BG_COLOR); //Set background color
        gdMenuStroke.setColors(gradient);
        gdMenuStroke1.setColors(gradient);
        startimage = new ImageView(this);
        startimage.setLayoutParams(new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        int applyDimension = (int) TypedValue.applyDimension(1, ICON_SIZE, getResources().getDisplayMetrics()); //Icon size
        startimage.getLayoutParams().height = applyDimension;
        startimage.getLayoutParams().width = applyDimension;
        startimage.requestLayout();
        startimage.setScaleType(ImageView.ScaleType.FIT_XY);
        byte[] decode = Base64.decode(Icon(), 0);
        startimage.setImageBitmap(BitmapFactory.decodeByteArray(decode, 0, decode.length));
        ((ViewGroup.MarginLayoutParams) startimage.getLayoutParams()).topMargin = convertDipToPixels(10);
        startimage.setOnTouchListener(onTouchListener());
        startimage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mCollapsed.setVisibility(View.GONE);
                mExpanded.setVisibility(View.VISIBLE);
                titleText.removeAllViews();
                titleText.addView(title);
                AddFeatures();
            }
        });
        patches = new LinearLayout(this);
        patches.setOrientation(LinearLayout.VERTICAL);
        titleText1 = new RelativeLayout(this);
        titleText1.setVerticalGravity(16);
        titleText = new LinearLayout(this);

        titleText.setPadding(0, 0, 0,  0);
        titleText.setVerticalGravity(LinearLayout.VERTICAL);
        titleText.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT,convertDipToPixels(25)));
        titleText.setBackground(gdMenuBody);

        layout = new RelativeLayout(this);
        layout.setPadding(0, 0, 0,  0);
        layout.setVerticalGravity(Gravity.TOP);
        layout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT,convertDipToPixels(4)));
        fuckedcumedpoloska = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{I, II, I, II});
        layout.setBackground(fuckedcumedpoloska);
        layout.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(final View v, final int left, final int top, final int right, final int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                fuckedcumedpoloska.setBounds(-2 * v.getWidth(), 0, v.getWidth(), v.getHeight());
                ValueAnimator animation = ValueAnimator.ofInt(0, 2 * v.getWidth());
                animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        fuckedcumedpoloska.setBounds(-2 * v.getWidth() + (int) animation.getAnimatedValue(), 0, v.getWidth() + (int) animation.getAnimatedValue(), v.getHeight());
                    }
                });
                animation.setRepeatMode(ValueAnimator.RESTART);
                animation.setInterpolator(new LinearInterpolator());
                animation.setRepeatCount(ValueAnimator.INFINITE);
                animation.setDuration(3000);
                animation.start();
            }
        });


        //********** Mod menu feature list **********
        scrollView = new ScrollView(this);
        //Auto size. To set size manually, change the width and height example 500, 500
        scrlLL = new LinearLayout.LayoutParams(MATCH_PARENT, convertDipToPixels(200));
        scrollView.setLayoutParams(scrlLL);
        scrollView.setBackgroundColor(MENU_FEATURE_BG_COLOR);
        //scrollView.setBackground(gdAnimation);
        patches = new LinearLayout(this);
        patches.setOrientation(LinearLayout.VERTICAL);
        patches.setBackgroundColor(NOCOLOR);

        //**********  Hide/Kill button **********

        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setPadding(0, -20, 0, -20);
        relativeLayout.setVerticalGravity(Gravity.CENTER);
        relativeLayout.setBackground(gdMenuBody1);

        Button hideBtn = new Button(this);
        hideBtn.setBackgroundColor(Color.TRANSPARENT);
        byte[] els$$ = Base64.decode("VjEgVkVSU0lPTg==",0);
        hideBtn.setText(new String(els$$));
        hideBtn.setTextColor(TEXT_COLOR);

        //********** Close button **********
        Button closeBtn = new Button(this);
        closeBtn.setBackgroundColor(Color.TRANSPARENT);
        byte[] els$4$ = Base64.decode("TWluaW1pemU=",0);
        closeBtn.setText(new String(els$4$));
        closeBtn.setTextColor(TEXT_COLOR);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mCollapsed.setVisibility(View.VISIBLE);
                mCollapsed.setAlpha(ICON_ALPHA);
                mExpanded.setVisibility(View.GONE);
            }
        });
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        layoutParams.addRule(ALIGN_PARENT_RIGHT);
        closeBtn.setLayoutParams(layoutParams);

        //********** Params **********
        //Variable to check later if the phone supports Draw over other apps permission
        int iparams = Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O ? 2038 : 2002;
        params = new WindowManager.LayoutParams(WRAP_CONTENT, WRAP_CONTENT, iparams, 8, -3);
        params.gravity = 51;
        params.gravity = 51;
        params.x = 0;
        params.y = 100;
        addlinears(getPlayer(),patches);
        //********** Adding view components **********
        rootFrame.addView(mRootContainer);
        mRootContainer.addView(mCollapsed);
        mRootContainer.addView(mExpanded);
        mCollapsed.addView(startimage);
        mExpanded.addView(layout);
        titleText1.addView(titleText);
        titleText.addView(title);
        AddFeatures();
        mExpanded.addView(titleText1);
        scrollView.addView(patches);
        mExpanded.addView(scrollView);
        relativeLayout.addView(hideBtn);
        relativeLayout.addView(closeBtn);
        mExpanded.addView(relativeLayout);
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mWindowManager.addView(rootFrame, params);

    }
    public void InseratbleBtn(String text, View.OnClickListener click){
        TextView title = new TextView(this);
        title.setPadding(0, 0, 0, 0);
        title.setText(text);
        title.setTextColor(TEXT_COLOR);
        title.setOnClickListener(click);
        title.setTypeface(Typeface.DEFAULT_BOLD);
        title.setTextSize(15.0f);
        title.setGravity(Gravity.CENTER);
        title.setBackground(MENU_FEATURE_BG_COLOR_GRADIENT);
        RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(convertDipToPixels(45), WRAP_CONTENT);
        rl.setMargins(20,0,0,0);
        title.setLayoutParams(rl);
        final LinearLayout cec = new LinearLayout(this);
        cec.setPadding(0, 0, 0,  0);
        cec.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT,convertDipToPixels(1)));
        cec.setOrientation(LinearLayout.HORIZONTAL);
        cec.setBackground(gdMenuStroke);
        titleText.addView(title);
        titleText.addView(cec);
    }
    private void AddFeatures(){
        for (final Category category : Category.values()) {
            InseratbleBtn(category.name(), new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    SwitchMenu(category);
                }
            });
        }}
    private void addlinears(String[] listFT, LinearLayout linearLayout) {
        for (int i = 0; i < listFT.length; i++) {
            String str = listFT[i];
            String[] strSplit = str.split("_");
            if (strSplit[1].equals("Toggle")) {
                linearLayout.addView(Switch(Integer.parseInt(strSplit[0]), strSplit[2]));
            } else if (strSplit[1].equals("SeekBar")) {
                linearLayout.addView(SeekBar(Integer.parseInt(strSplit[0]), strSplit[2], Integer.parseInt(strSplit[3]), Integer.parseInt(strSplit[4])));
            } else if (strSplit[1].equals("Button")) {
                linearLayout.addView(Button(Integer.parseInt(strSplit[0]), strSplit[2]));
            } else if (strSplit[1].equals("ButtonLink")) {
                linearLayout.addView(ButtonLink(strSplit[2], strSplit[3]));
            } else if (strSplit[1].equals("ButtonOnOff")) {
                linearLayout.addView(ButtonOnOff(Integer.parseInt(strSplit[0]), strSplit[2]));
            } else if (strSplit[1].equals("Spinner")) {
                linearLayout.addView(RichTextView(strSplit[2]));
                linearLayout.addView(Spinner(Integer.parseInt(strSplit[0]), strSplit[2], strSplit[3]));
            } else if (strSplit[1].equals("InputValue")) {
                linearLayout.addView(TextField(Integer.parseInt(strSplit[0]), strSplit[2]));
            } else if (strSplit[1].equals("CheckBox")) {
                linearLayout.addView(CheckBox(Integer.parseInt(strSplit[0]), strSplit[2]));
            } else if (strSplit[1].equals("Category")) {
                linearLayout.addView(Category(strSplit[2]));
            } else if (strSplit[1].equals("RichTextView")) {
                linearLayout.addView(RichTextView(strSplit[2]));
            } else if (strSplit[1].equals("RichWebView")) {
                linearLayout.addView(RichWebView(strSplit[2]));
            } else if (strSplit[1].equals("RadioButton")) {
                linearLayout.addView(RadioButton(Integer.parseInt(strSplit[0]), strSplit[2], strSplit[3]));
            }
        }
    }

    private View.OnTouchListener onTouchListener() {
        return new View.OnTouchListener() {
            final View collapsedView = mCollapsed;
            final View expandedView = mExpanded;
            private float initialTouchX, initialTouchY;
            private int initialX, initialY;

            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initialX = params.x;
                        initialY = params.y;
                        initialTouchX = motionEvent.getRawX();
                        initialTouchY = motionEvent.getRawY();
                        return true;
                    case MotionEvent.ACTION_UP:
                        int rawX = (int) (motionEvent.getRawX() - initialTouchX);
                        int rawY = (int) (motionEvent.getRawY() - initialTouchY);

                        //The check for Xdiff <10 && YDiff< 10 because sometime elements moves a little while clicking.
                        //So that is click event.
                        if (rawX < 10 && rawY < 10 && isViewCollapsed()) {
                            //When user clicks on the image view of the collapsed layout,
                            //visibility of the collapsed layout will be changed to "View.GONE"
                            //and expanded view will become visible.
                            try {
                                collapsedView.setVisibility(View.GONE);
                                expandedView.setVisibility(View.VISIBLE);
                            } catch (NullPointerException e) {

                            }
                        }
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        //Calculate the X and Y coordinates of the view.
                        params.x = initialX + ((int) (motionEvent.getRawX() - initialTouchX));
                        params.y = initialY + ((int) (motionEvent.getRawY() - initialTouchY));
                        //Update the layout with new X & Y coordinate
                        mWindowManager.updateViewLayout(rootFrame, params);
                        return true;
                    default:
                        return false;
                }
            }
        };
    }

    //Dialog for changing value
    private void initAlertDiag() {
        //LinearLayout
        LinearLayout linearLayout1 = new LinearLayout(this);
        linearLayout1.setPadding(5, 5, 5, 5);
        linearLayout1.setOrientation(LinearLayout.VERTICAL);
        linearLayout1.setBackgroundColor(Color.BLACK);
        LinearLayout linearLayout5 = new LinearLayout(this);
        linearLayout5.setOrientation(LinearLayout.VERTICAL);
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.addView(linearLayout5);
        final TextView textView = new TextView(this);
        textView.setText("Tap OK to apply changes. Tap outside to cancel");
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setTextColor(TEXT_COLOR_2);
        edittextvalue = new EditText(this);
        edittextvalue.setMaxLines(1);
        edittextvalue.setWidth(convertDipToPixels(300));
        edittextvalue.setTextColor(TEXT_COLOR);
        edittextvalue.setHintTextColor(HintTxtColor);
        edittextvalue.setInputType(InputType.TYPE_CLASS_NUMBER);
        edittextvalue.setKeyListener(DigitsKeyListener.getInstance("0123456789-"));
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(10);
        edittextvalue.setFilters(FilterArray);
        Button button = new Button(this);
        button.setBackgroundColor(BTN_COLOR);
        button.setTextColor(TEXT_COLOR_2);
        button.setText("SET");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputFieldTxtValue.setValue(Integer.parseInt(edittextvalue.getText().toString()));
                inputFieldTextView.setText(Html.fromHtml("<b>"+ "Input " +inputFieldFeatureName + ": "+ edittextvalue.getText().toString()+ "</b>"));
                alert.dismiss();
                SavedPrefs.changeFeatureInt(inputFieldFeatureName, inputFieldFeatureNum, Integer.parseInt(edittextvalue.getText().toString()));
                localChanges(inputFieldFeatureNum, false, Integer.parseInt(edittextvalue.getText().toString()));
            }
        });
        final GradientDrawable background = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{I, II, I, II});
        button.setBackground(background);
        button.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(final View v, final int left, final int top, final int right, final int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                background.setBounds(-2 * v.getWidth(), 0, v.getWidth(), v.getHeight());
                ValueAnimator animation = ValueAnimator.ofInt(0, 2 * v.getWidth());
                animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        background.setBounds(-2 * v.getWidth() + (int) animation.getAnimatedValue(), 0, v.getWidth() + (int) animation.getAnimatedValue(), v.getHeight());
                    }
                });
                animation.setRepeatMode(ValueAnimator.RESTART);
                animation.setInterpolator(new LinearInterpolator());
                animation.setRepeatCount(ValueAnimator.INFINITE);
                animation.setDuration(3000);
                animation.start();
            }
        });
        alert = new AlertDialog.Builder(this, 2).create();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(alert.getWindow()).setType(Build.VERSION.SDK_INT >= 26 ? 2038 : 2002);
        }
        linearLayout1.addView(textView);
        linearLayout1.addView(edittextvalue);
        linearLayout1.addView(button);
        alert.setView(linearLayout1);
    }

    private View Switch(final int featureNum, final String featureName) {
        final Switch switchR = new Switch(this);
        ColorStateList buttonStates = new ColorStateList(
                new int[][]{
                        new int[]{-android.R.attr.state_enabled},
                        new int[]{android.R.attr.state_checked},
                        new int[]{}
                },
                new int[]{
                        Color.BLUE,
                        ToggleON, // ON
                        ToggleOFF // OFF
                }
        );
        //Set colors of the switch. Comment out if you don't like it
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            switchR.getThumbDrawable().setTintList(buttonStates);
            switchR.getTrackDrawable().setTintList(buttonStates);
        }

        switchR.setText(featureName);
        switchR.setTextColor(TEXT_COLOR_2);
        switchR.setPadding(10, 5, 0, 5);
        switchR.setChecked(SavedPrefs.loadPrefBoolean(featureName, featureNum));
        switchR.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                SavedPrefs.changeFeatureBoolean(featureName, featureNum, isChecked);
            }
        });
        return switchR;
    }

    private View SeekBar(final int featureNum, final String featureName, final int min, int max) {
        int loadedProg = SavedPrefs.loadPrefInt(featureName, featureNum);
        LinearLayout linearLayout6 = new LinearLayout(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
        layoutParams.setMargins(20,10,10,30);
        linearLayout6.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout6.setPadding(10, 5, 0, 5);
        RelativeLayout relativeLayout5 = new RelativeLayout(this);
        relativeLayout5.setPadding(10, 5, 10, 5);
        relativeLayout5.setHorizontalGravity(16);
        relativeLayout5.setBackgroundColor(MENU_FEATURE_BG_COLOR);

        final TextView textView = new TextView(this);
        textView.setText(Html.fromHtml("<font face='roboto'>" + featureName + " <font color='" + TEXT_COLOR_2 +"'>" + ((loadedProg == 0) ? min : loadedProg) + "</font>"));
        textView.setTextColor(TEXT_COLOR_2);
        SeekBar seekBar = new SeekBar(this);
        seekBar.setLayoutParams(layoutParams);
        seekBar.setPadding(400, 0, 0, 0);
        seekBar.setMax(max);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        seekBar.setMin(min); //setMin for Oreo and above
        seekBar.setProgress((loadedProg == 0) ? min : loadedProg);
        seekBar.getThumb().setColorFilter(BtnON, PorterDuff.Mode.SRC_ATOP);
        seekBar.getProgressDrawable().setColorFilter(II, PorterDuff.Mode.SRC_ATOP);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekBar.getProgressDrawable().setColorFilter(II, PorterDuff.Mode.SRC_ATOP);
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                seekBar.getProgressDrawable().setColorFilter(II, PorterDuff.Mode.SRC_ATOP);
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                seekBar.getProgressDrawable().setColorFilter(II, PorterDuff.Mode.SRC_ATOP);
                seekBar.setProgress(i < min ? min : i);
                seekBar.getThumb().setColorFilter(BtnON, PorterDuff.Mode.SRC_ATOP);
                SavedPrefs.changeFeatureInt(featureName, featureNum, i < min ? min : i);
                textView.setText(Html.fromHtml("<font face='roboto'>" + featureName + " <font color='" + TEXT_COLOR_2 +"'>" + (i < min ? min : i) + "</font>"));
            }
        });
        relativeLayout5.addView(textView);
        relativeLayout5.addView(seekBar);

        return relativeLayout5;
    }

    private View Button(final int featureNum, final String featureName) {
        final Button button = new Button(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(MATCH_PARENT, convertDipToPixels(23));
        layoutParams.setMargins(0, 1, 0, 1);
        button.setLayoutParams(layoutParams);
        button.setTextColor(TEXT_COLOR_2);
        button.setAllCaps(false); //Disable caps to support html
        button.setText(Html.fromHtml(featureName));
        button.setBackgroundColor(BTN_COLOR);
        layoutParams.setMargins(0, 1, 0, 1);
        button.setLayoutParams(layoutParams);
        button.setTextColor(TEXT_COLOR_2);
        button.setAllCaps(false); //Disable caps to support html
        button.setPadding(0,0,0,0);
        button.setGravity(Gravity.CENTER);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SavedPrefs.changeFeatureInt(featureName, featureNum, 0);
            }
        });

        return button;
    }

    private View ButtonLink(final String featureName, final String url) {
        final Button button = new Button(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(MATCH_PARENT, convertDipToPixels(23));
        layoutParams.setMargins(7, 5, 7, 5);
        button.setLayoutParams(layoutParams);
        button.setAllCaps(false); //Disable caps to support html
        button.setTextColor(TEXT_COLOR_2);
        button.setGravity(Gravity.CENTER);
        button.setText(featureName);
        button.setBackgroundColor(BTN_COLOR);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
        return button;
    }

    private View ButtonOnOff(final int featureNum, String featureName) {
        final Button button = new Button(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(MATCH_PARENT, convertDipToPixels(23));
        layoutParams.setMargins(0, 1, 0, 1);
        button.setLayoutParams(layoutParams);
        button.setTextColor(TEXT_COLOR_2);
        button.setAllCaps(false); //Disable caps to support html
        button.setPadding(0,0,0,0);
        button.setGravity(Gravity.CENTER);
        button.setTypeface(Typeface.DEFAULT_BOLD);

        final String finalFeatureName = featureName.replace("OnOff_", "");
        boolean isOn = SavedPrefs.loadPrefBoolean(featureName, featureNum);
        if (isOn) {
            button.setText(finalFeatureName);
            button.setBackgroundColor(BtnON);
            isOn = false;
        } else {
            button.setText(finalFeatureName);
            button.setBackgroundColor(BtnOFF);
            isOn = true;
        }
        final boolean finalIsOn = isOn;
        button.setOnClickListener(new View.OnClickListener() {
            boolean isOn = finalIsOn;

            public void onClick(View v) {
                SavedPrefs.changeFeatureBoolean(finalFeatureName, featureNum, isOn);
                if (isOn) {
                    button.setText(finalFeatureName);
                    button.setBackgroundColor(BtnON);
                    isOn = false;
                } else {
                    button.setText(finalFeatureName);
                    button.setBackgroundColor(BtnOFF);
                    isOn = true;
                }
            }
        });

        return button;
    }

    private View Spinner(final int featureNum, final String featureName, final String list) {
        final List<String> lists = new LinkedList<>(Arrays.asList(list.split(",")));
        LinearLayout linearLayout2 = new LinearLayout(this);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
        layoutParams2.setMargins(10, 2, 10, 5);
        linearLayout2.setOrientation(LinearLayout.VERTICAL);
        linearLayout2.setBackgroundColor(BTN_COLOR);
        linearLayout2.setLayoutParams(layoutParams2);

        final Spinner spinner = new Spinner(this, Spinner.MODE_DROPDOWN);
        spinner.setPadding(5, 10, 5, 8);
        spinner.setLayoutParams(layoutParams2);
        spinner.getBackground().setColorFilter(1, PorterDuff.Mode.SRC_ATOP); //trick to show white down arrow color
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, lists);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);
        spinner.setSelection(SavedPrefs.loadPrefInt(featureName, featureNum));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                SavedPrefs.changeFeatureInt(spinner.getSelectedItem().toString(), featureNum, position);
                ((TextView) parentView.getChildAt(0)).setTextColor(TEXT_COLOR_2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        linearLayout2.addView(spinner);
        return linearLayout2;
    }

    private View TextField(final int feature, final String featureName) {
        RelativeLayout relativeLayout2 = new RelativeLayout(this);
        relativeLayout2.setGravity(Gravity.CENTER);
        relativeLayout2.setBackground(MENU_FEATURE_BG_COLOR_GRADIENT);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(MATCH_PARENT, convertDipToPixels(23));
        layoutParams.setMargins(3,3,3,3);
        relativeLayout2.setGravity(CENTER_HORIZONTAL);
        final TextView textView = new TextView(this);
        int num = SavedPrefs.loadPrefInt(featureName, feature);
        textView.setText(Html.fromHtml("<b>"+ "Input " +featureName + ": "+ num+ "</b>"));
        textView.setTextColor(TEXT_COLOR);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setBackgroundColor(MENU_FEATURE_BG_COLOR);
        textView.setLayoutParams(layoutParams);
        textView.setGravity(Gravity.CENTER);

        final EditTextValue edittextval = new EditTextValue();
        textView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                alert.show();
                inputFieldTextView = textView;
                inputFieldFeatureNum = feature;
                inputFieldFeatureName = featureName;
                inputFieldTxtValue = edittextval;
                edittextvalue.setText(String.valueOf(edittextval.getValue()));
            }
        });

        relativeLayout2.addView(textView);
        return relativeLayout2;
    }

    private View CheckBox(final int featureNum, final String featureName) {
        final CheckBox checkBox = new CheckBox(this);
        checkBox.setText(featureName);
        checkBox.setTextColor(TEXT_COLOR_2);
        checkBox.setChecked(SavedPrefs.loadPrefBoolean(featureName, featureNum));
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox.isChecked()) {
                    SavedPrefs.changeFeatureBoolean(featureName, featureNum, isChecked);
                } else {
                    SavedPrefs.changeFeatureBoolean(featureName, featureNum, isChecked);
                }
            }
        });
        return checkBox;
    }

    private View RadioButton(final int featureNum, String featureName, final String list) {
        //Credit: LoraZalora
        final List<String> lists = new LinkedList<>(Arrays.asList(list.split(",")));

        final TextView textView = new TextView(this);
        textView.setText(featureName + ":");
        textView.setTextColor(TEXT_COLOR_2);

        final RadioGroup radioGroup = new RadioGroup(this);
        radioGroup.setPadding(10, 5, 10, 5);
        radioGroup.setOrientation(LinearLayout.VERTICAL);
        radioGroup.addView(textView);

        for (int i = 0; i < lists.size(); i++) {
            final RadioButton Radioo = new RadioButton(this);
            final String finalFeatureName = featureName, radioName = lists.get(i);
            View.OnClickListener first_radio_listener = new View.OnClickListener() {
                public void onClick(View v) {
                    textView.setText(finalFeatureName + ": " + radioName);
                    textView.setText(Html.fromHtml("<font face='roboto'>" + finalFeatureName + ": <font color='" + NumberTxt +"'>" + radioName + "</font>"));
                    SavedPrefs.changeFeatureInt(finalFeatureName, featureNum, radioGroup.indexOfChild(Radioo));
                }
            };
            System.out.println(lists.get(i));
            Radioo.setText(lists.get(i));
            Radioo.setTextColor(Color.LTGRAY);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                Radioo.setButtonTintList(ColorStateList.valueOf(RadioColor));
            Radioo.setOnClickListener(first_radio_listener);
            radioGroup.addView(Radioo);
        }

        return radioGroup;
    }



    private View Category(String text) {
        LinearLayout linearLayout7 = new LinearLayout(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
        linearLayout7.setOrientation(LinearLayout.VERTICAL);
        TextView title3 = new TextView(this);
        title3.setText("                                                                                         ");
        title3.setBackground(MENU_STROKE);
        title3.setTextColor(TEXT_COLOR);
        title3.setTextSize(3.0f);
        title3.setGravity(Gravity.TOP);
        title3.setPadding(1000, 0, 1000, 0);
        TextView textView = new TextView(this);
        textView.setText(Html.fromHtml(text));
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(15.0f);
        textView.setTextColor(TEXT_COLOR_2);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setPadding(0, 5, 0, 5);
        final GradientDrawable background = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{I, II, I, II});
        linearLayout7.setBackground(background);
        linearLayout7.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(final View v, final int left, final int top, final int right, final int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                background.setBounds(-2 * v.getWidth(), 0, v.getWidth(), v.getHeight());
                ValueAnimator animation = ValueAnimator.ofInt(0, 2 * v.getWidth());
                animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        background.setBounds(-2 * v.getWidth() + (int) animation.getAnimatedValue(), 0, v.getWidth() + (int) animation.getAnimatedValue(), v.getHeight());
                    }
                });
                animation.setRepeatMode(ValueAnimator.RESTART);
                animation.setInterpolator(new LinearInterpolator());
                animation.setRepeatCount(ValueAnimator.INFINITE);
                animation.setDuration(3000);
                animation.start();
            }
        });

        linearLayout7.addView(textView);
        return linearLayout7;
    }

    private View RichTextView(String text) {
        TextView textView = new TextView(this);
        textView.setText(Html.fromHtml(text));
        textView.setTextColor(TEXT_COLOR_2);
        textView.setPadding(10, 5, 10, 5);
        return textView;
    }

    private View RichWebView(String text) {
        WebView wView = new WebView(this);
        wView.loadData(text, "text/html", "utf-8");
        wView.setBackgroundColor(0x00000000); //Transparent
        wView.setPadding(0, 5, 0, 5);
        wView.getSettings().setAppCacheEnabled(false);
        wView.requestLayout();
        return wView;
    }

    public void RefuckMenu(){
        mExpanded.setVisibility(View.GONE);
        fuckedcumedpoloska = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{Color.argb(255,red,green,blue), II, Color.argb(255,red,green,blue), II});
        layout.setBackground(fuckedcumedpoloska);
        layout.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(final View v, final int left, final int top, final int right, final int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                fuckedcumedpoloska.setBounds(-2 * v.getWidth(), 0, v.getWidth(), v.getHeight());
                ValueAnimator animation = ValueAnimator.ofInt(0, 2 * v.getWidth());
                animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        fuckedcumedpoloska.setBounds(-2 * v.getWidth() + (int) animation.getAnimatedValue(), 0, v.getWidth() + (int) animation.getAnimatedValue(), v.getHeight());
                    }
                });
                animation.setRepeatMode(ValueAnimator.RESTART);
                animation.setInterpolator(new LinearInterpolator());
                animation.setRepeatCount(ValueAnimator.INFINITE);
                animation.setDuration(3000);
                animation.start();
            }
        });
        mExpanded.setVisibility(View.VISIBLE);
        patches.removeAllViews();
        addlinears(getSettings(),patches);

    }

    private void localChanges(int featureNum, boolean toggle, int value) {
        switch (featureNum) {
            case -110:
                I = Color.argb(255,value,green,blue);
                red = value;
                RefuckMenu();
                break;
            case -111:
                I = Color.argb(255,red,green,value);
                blue = value;
                RefuckMenu();
                break;
            case -112:
                I = Color.argb(255,red,value,blue);
                green = value;
                RefuckMenu();
                break;
            case -210:
                II = Color.argb(255,value,greeni,bluei);
                redi = value;
                RefuckMenu();
                break;
            case -211:
                II = Color.argb(255,redi,greeni,value);
                bluei = value;
                RefuckMenu();
                break;
            case -212:
                II = Color.argb(255,redi,value,bluei);
                greeni = value;
                RefuckMenu();
                break;
        }
    }
    //Override our Start Command so the Service doesnt try to recreate itself when the App is closed
    public int onStartCommand(Intent intent, int i, int i2) {
        return Service.START_NOT_STICKY;
    }

    private boolean isViewCollapsed() {
        return rootFrame == null || mCollapsed.getVisibility() == View.VISIBLE;
    }

    //For our image a little converter
    private int convertDipToPixels(int i) {
        return (int) ((((float) i) * getResources().getDisplayMetrics().density) + 0.5f);
    }

    private int dp(int i) {
        return (int) TypedValue.applyDimension(1, (float) i, getResources().getDisplayMetrics());
    }

    //Check if we are still in the game. If now our menu and menu button will dissapear
    private boolean isNotInGame() {
        RunningAppProcessInfo runningAppProcessInfo = new RunningAppProcessInfo();
        ActivityManager.getMyMemoryState(runningAppProcessInfo);
        return runningAppProcessInfo.importance != 100;
    }



    //Destroy our View 3
    public void onDestroy() {
        super.onDestroy();
        if (rootFrame != null) {
            mWindowManager.removeView(rootFrame);
        }
        stopSelf();
    }

    //Same as above so it wont crash in the background and therefore use alot of Battery life
    public void onTaskRemoved(Intent intent) {
        super.onTaskRemoved(intent);
        stopSelf();
    }

    private void Thread() {
        if (rootFrame == null) {
            return;
        }
        if (isNotInGame()) {
            rootFrame.setVisibility(View.INVISIBLE);
        } else {
            rootFrame.setVisibility(View.VISIBLE);
        }
    }

    private class EditTextValue {
        private int val;

        public void setValue(int i) {
            val = i;
        }

        public int getValue() {
            return val;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}