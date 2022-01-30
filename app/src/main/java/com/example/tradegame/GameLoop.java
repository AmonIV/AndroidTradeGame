package com.example.tradegame;

import android.animation.ObjectAnimator;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class GameLoop implements Runnable{

    int PlayerLife = 5;
    int LeftShipLife = 3;
    int RightShipLife = 3;
    public boolean BattleIsActive = true;
    public boolean LeftShipIsAlive = true;
    public boolean RightShipIsAlive = true;
    public boolean PlayerIsAlive = true;
    public boolean LeftAttackTriggered = false;
    public boolean RightAttackTriggered = false;
    public int LeftRandomValue = 0;
    public int LeftChanceBooster =0;
    public int RightRandomValue = 0;
    public int RightChanceBooster =0;
    public boolean LeftEnemyShipInPosition = false;
    public boolean RightEnemyShipInPosition = false;
    public boolean LeftEnemyReadyToFire = false;
    public boolean RightEnemyReadyToFire = false;
    public boolean LeftEnemyCannonballTryHit = false;
    public boolean RightEnemyCannonballTryHit = false;
    public boolean DodgingRight = false;
    public boolean DodgingLeft = false;
    public boolean HittingLeft = false;
    public boolean HittingRight = false;
    int ScreenWidth;
    ImageView LeftEnemyShip;
    ImageView RightEnemyShip;
    ImageView PlayerShip;
    Button LeftFireButton;
    Button LeftDodgeButton;
    Button RightFireButton;
    Button RightDodgeButton;
    TextView WonScreenText;
    Button WonScreenButton;
    TextView LostScreenText;
    Button LostScreenButton;
    Thread GameThread;

    public GameLoop(int InScreenWidth,ImageView InLeftEnemyShip, ImageView InRightEnemyShip, ImageView InPlayerShip, Button InLeftFireButton, Button InLeftDodgeButton, Button InRightFireButton, Button InRightDodgeButton, TextView InWonScreenText, Button InWonScreenButton, TextView InLostScreenText, Button InLostScreenButton)
    {
        ScreenWidth = InScreenWidth;
        LeftEnemyShip = InLeftEnemyShip;
        RightEnemyShip = InRightEnemyShip;
        PlayerShip = InPlayerShip;
        LeftFireButton = InLeftFireButton;
        LeftDodgeButton = InLeftDodgeButton;
        RightFireButton = InRightFireButton;
        RightDodgeButton = InRightDodgeButton;
        WonScreenText = InWonScreenText;
        WonScreenButton = InWonScreenButton;
        LostScreenText = InLostScreenText;
        LostScreenButton = InLostScreenButton;

        LeftFireButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerFireLeft(PlayerShip);
                LeftFireButton.setEnabled(false);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        LeftFireButton.setEnabled(true);
                    }
                }, 2000);
            }
        });

        RightFireButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerFireRight(PlayerShip);
                RightFireButton.setEnabled(false);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        RightFireButton.setEnabled(true);
                    }
                }, 2000);
            }
        });

        LeftDodgeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DodgingLeft = true;
                LeftDodgeButton.setEnabled(false);
                RightDodgeButton.setEnabled(false);
                AnimatePLayerDodgingLeft(PlayerShip);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        DodgingLeft = false;
                    }
                }, 1500);
                final Handler handler2 = new Handler();
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        LeftDodgeButton.setEnabled(true);
                        RightDodgeButton.setEnabled(true);
                    }
                }, 2000);
            }
        });

        RightDodgeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DodgingRight = true;
                LeftDodgeButton.setEnabled(false);
                RightDodgeButton.setEnabled(false);
                AnimatePLayerDodgingRight(PlayerShip);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        DodgingRight = false;
                    }
                }, 1500);
                final Handler handler2 = new Handler();
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        LeftDodgeButton.setEnabled(true);
                        RightDodgeButton.setEnabled(true);
                    }
                }, 2000);
            }
        });


        GameThread = new Thread(this);
        GameThread.start();
    }

    @Override
    public void run() {
        while(BattleIsActive)
        {
            Loop();
            RefreshRate();
        }

    }

    public void Loop()
    {
        Random random = new Random();
        if(!LeftAttackTriggered && LeftShipIsAlive)
        {
            LeftRandomValue = random.nextInt(1000);
            LeftRandomValue += LeftChanceBooster;
            if (LeftRandomValue > 1000) {
                LeftRandomValue = 1000;
            }
        }
        if(!RightAttackTriggered && RightShipIsAlive) {
            RightRandomValue = random.nextInt(1000);
            RightRandomValue += RightChanceBooster;
            if (RightRandomValue > 1000) {
                RightRandomValue = 1000;
            }
        }
        if (LeftRandomValue >= 999)
        {
            LeftAttackTriggered = true;
            LeftRandomValue = 0;
            LeftChanceBooster = 0;
            LeftShipAttack(ScreenWidth,LeftEnemyShip);
        }

        if (RightRandomValue >= 999)
        {
            RightAttackTriggered = true;
            RightRandomValue = 0;
            RightChanceBooster = 0;
            RightShipAttack(ScreenWidth,RightEnemyShip);
        }

        if(LeftEnemyShipInPosition && LeftEnemyReadyToFire)
        {
            LeftEnemyReadyToFire = false;
            LeftEnemyShipInPosition = false;
            LeftEnemyShipFire(LeftEnemyShip);
            LeftEnemyShipRetreat(LeftEnemyShip,ScreenWidth);
        }

        if(RightEnemyShipInPosition && RightEnemyReadyToFire)
        {
            RightEnemyReadyToFire = false;
            RightEnemyShipInPosition = false;
            RightEnemyShipFire(RightEnemyShip);
            RightEnemyShipRetreat(RightEnemyShip,ScreenWidth);
        }

        if(!LeftAttackTriggered)
        {
            LeftChanceBooster++;
        }

        if(!RightAttackTriggered)
        {
            RightChanceBooster++;
        }

        if(HittingLeft)
        {
            HittingLeft = false;
            LeftShipLife--;
            EnemyShipHitAnimation(LeftEnemyShip);
            if(LeftShipLife <= 0)
            {
                LeftShipIsAlive = false;
            }
        }

        if(HittingRight)
        {
            HittingRight = false;
            RightShipLife--;
            EnemyShipHitAnimation(RightEnemyShip);
            if(RightShipLife <= 0)
            {
                RightShipIsAlive = false;
            }
        }

        if(LeftEnemyCannonballTryHit)
        {
            LeftEnemyCannonballTryHit = false;
            if(!DodgingRight)
            {
                PlayerLife--;
                PlayerHitAnimation(PlayerShip);
            }
        }

        if(RightEnemyCannonballTryHit)
        {
            RightEnemyCannonballTryHit = false;
            if(!DodgingLeft)
            {
                PlayerLife--;
                PlayerHitAnimation(PlayerShip);
            }
        }

        if(!LeftShipIsAlive)
        {
            EnemyShipDeadAnimation(LeftEnemyShip);
        }

        if(!RightShipIsAlive)
        {
            EnemyShipDeadAnimation(RightEnemyShip);
        }

        if(!LeftShipIsAlive && !RightShipIsAlive)
        {
            BattleIsActive = false;
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    WonScreenButton.setVisibility(View.VISIBLE);
                    WonScreenButton.setClickable(true);
                    WonScreenText.setVisibility(View.VISIBLE);
                }
            });
        }

        if(PlayerLife<=0)
        {
            PlayerIsAlive = false;
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    LostScreenButton.setVisibility(View.VISIBLE);
                    LostScreenButton.setClickable(true);
                    LostScreenText.setVisibility(View.VISIBLE);
                }
            });
        }

        if(!PlayerIsAlive)
        {
            BattleIsActive = false;
        }
    }

    public void RefreshRate()
    {

        try {
            GameThread.sleep(41);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void LeftShipAttack(int ScreenWidth, ImageView Ship)
    {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {

                ObjectAnimator animator = ObjectAnimator.ofFloat(Ship,"translationX",ScreenWidth/10);
                animator.setDuration(2000);
                animator.start();
            }
        });
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                LeftEnemyShipInPosition = true;
            }
        }, 2000);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                LeftEnemyReadyToFire = true;
            }
        }, 2500);
    }

    public void RightShipAttack(int ScreenWidth, ImageView Ship)
    {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator animator = ObjectAnimator.ofFloat(Ship,"translationX",(ScreenWidth/10)*(-1));
                animator.setDuration(2000);
                animator.start();
            }
        });
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                RightEnemyShipInPosition = true;
            }
        }, 2000);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                RightEnemyReadyToFire = true;
            }
        }, 2500);
    }

    public void LeftEnemyShipFire(ImageView Ship)
    {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Ship.setImageResource(R.drawable.enemyshiprightfire);

            }
        });
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Ship.setImageResource(R.drawable.enemyship);
            }
        }, 200);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                LeftEnemyCannonballTryHit = true;
            }
        }, 600);
    }

    public void RightEnemyShipFire(ImageView Ship)
    {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Ship.setImageResource(R.drawable.enemyshipleftfire);
            }
        });
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Ship.setImageResource(R.drawable.enemyship);
            }
        }, 200);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                RightEnemyCannonballTryHit = true;
            }
        }, 600);
    }

    public void LeftEnemyShipRetreat(ImageView Ship,int ScreenWidth)
    {
        LeftEnemyReadyToFire = false;
        LeftEnemyShipInPosition = false;
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                        ObjectAnimator animator = ObjectAnimator.ofFloat(Ship,"translationX",0);
                        animator.setDuration(2000);
                        animator.start();
            }
        });
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                LeftAttackTriggered = false;
            }
        }, 2000);
    }

    public void RightEnemyShipRetreat(ImageView Ship,int ScreenWidth)
    {
        RightEnemyReadyToFire = false;
        RightEnemyShipInPosition = false;
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator animator = ObjectAnimator.ofFloat(Ship,"translationX",0);
                animator.setDuration(2000);
                animator.start();
            }
        });
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                RightAttackTriggered = false;
            }
        }, 2000);
    }

    public void PlayerFireLeft(ImageView Ship)
    {
        Ship.setBackgroundResource(R.drawable.playershipleftfire);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Ship.setBackgroundResource(R.drawable.playership);
            }
        }, 200);
        if(!DodgingRight) {
            final Handler handler2 = new Handler();
            handler2.postDelayed(new Runnable() {
                @Override
                public void run() {
                    HittingLeft = true;
                }
            }, 600);
        }
    }

    public void PlayerFireRight(ImageView Ship)
    {
        Ship.setBackgroundResource(R.drawable.playershiprightfire);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Ship.setBackgroundResource(R.drawable.playership);
            }
        }, 200);
        if(!DodgingLeft) {
            final Handler handler2 = new Handler();
            handler2.postDelayed(new Runnable() {
                @Override
                public void run() {
                    HittingRight = true;
                }
            }, 600);
        }
    }

    public void AnimatePLayerDodgingLeft(ImageView Ship)
    {
        ObjectAnimator animator = ObjectAnimator.ofFloat(Ship,"translationX",(ScreenWidth/10)*(-1));
        animator.setDuration(1000);
        animator.start();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator animator = ObjectAnimator.ofFloat(Ship,"translationX",0);
                animator.setDuration(1000);
                animator.start();
            }
        }, 1000);
    }

    public void AnimatePLayerDodgingRight(ImageView Ship)
    {
        ObjectAnimator animator = ObjectAnimator.ofFloat(Ship,"translationX",(ScreenWidth/10));
        animator.setDuration(1000);
        animator.start();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator animator = ObjectAnimator.ofFloat(Ship,"translationX",0);
                animator.setDuration(1000);
                animator.start();
            }
        }, 1000);
    }

    public void PlayerHitAnimation(ImageView Ship)
    {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Ship.setImageResource(R.drawable.playershiphit);
            }
        });
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Ship.setImageResource(R.drawable.playership);
            }
        }, 200);
    }

    public void EnemyShipHitAnimation(ImageView Ship)
    {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Ship.setImageResource(R.drawable.enemyshiphit);
            }
        });
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Ship.setImageResource(R.drawable.enemyship);
            }
        }, 200);
    }

    public void EnemyShipDeadAnimation(ImageView Ship)
    {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Ship.setImageResource(R.drawable.enemyshiphit);
            }
        });
    }
}
