import pygame as pg
import math as m
pg.init()
pg.font.init()

screen = pg.display.set_mode((800,600))
clock = pg.time.Clock()
running = True






# Paddle Information
width = 10
height = 100

Paddles = []
Paddle1 = pg.Rect(0, screen.get_height()/2-height, width, screen.get_height()/2-height)
Paddle2 = pg.Rect(screen.get_width() - width, screen.get_height()/2-height, screen.get_width(), screen.get_height()/2-height)

Paddles.append(Paddle1)
Paddles.append(Paddle2)

# Ball Information
Ballcent = pg.Vector2(screen.get_width()/2, screen.get_height()/2)
velocityx = 10
velocityy = 0
dt = 0

keys = pg.key.get_pressed()

font = pg.font.SysFont("Arial", 60)

leftwin = font.render("Player 1 Wins!", True, "black")
rightwin = font.render("Player 2 Wins!", True, "black")
scoreleft = 0
scoreright = 0

pause = False

maxspeed = 50





while running:
    for event in pg.event.get():
        if event.type == pg.QUIT:
            running = False

    # Objects Initialization
    screen.fill("cyan")
    score1 = font.render(str(scoreleft), True, "black")
    score2 = font.render(str(scoreright), True, "black")

    screen.blit(score1, (screen.get_width() / 2 - 100, 100))
    screen.blit(score2, (screen.get_width() / 2 + 100, 100))
    Ball = pg.draw.circle(screen, "black",Ballcent,20 )
    pg.draw.rect(screen, "red",Paddle1)
    pg.draw.rect(screen, "red", Paddle2)
    keys = pg.key.get_pressed()

    speed = m.hypot(velocityx, velocityy)
    # Endgame Condition
    if scoreleft >= 10 or scoreright >= 10:
        pause = True
        if scoreleft >= 10:
            screen.blit(leftwin, (screen.get_width() / 2-300, screen.get_height() / 2))

        elif scoreright >= 10:
            screen.blit(rightwin, (screen.get_width() / 2, screen.get_height() / 2))
    # Pause
    if keys[pg.K_SPACE] and pause == False:
        pause = True
    elif keys[pg.K_k] and pause == True:
        pause = False
    if pause == False:
    # Ball Movement
        dist = m.hypot(780, Ballcent.y)
        speed = m.hypot(velocityx, velocityy)
        if speed < maxspeed:
            Ballcent.x += velocityx * dt * 50
            Ballcent.y += velocityy * dt * 50
        else:
            Ballcent.x += maxspeed*m.cos(m.atan(velocityx/velocityy))
            Ballcent.y += maxspeed*m.sin(m.atan(velocityx/velocityy))

        # Collision Logic
        for paddle in Paddles:
            if Ball.colliderect(paddle):
                velocityx = -velocityx
                maxdeg = m.asin(Ballcent.y/dist)
                if Ballcent.x > 400:
                    Ball.right = paddle.left
                    Ballcent.x = paddle.left - 20
                    incidence = (Paddle1.y+height/2-Ballcent.y)/(height/2)*maxdeg
                    velocityy = -velocityx*m.tan(incidence)
                elif Ballcent.x < 400:
                    Ball.left = paddle.right
                    incidence = (Paddle2.y + height / 2 - Ballcent.y) / (height / 2) * maxdeg
                    velocityy = -velocityx * m.tan(incidence)
                    Ballcent.x = paddle.right + 20
        # Scoring / Ball Reset
        if Ballcent.x <= 0:
            scoreright += 1
            Ballcent.x = screen.get_width()/2
            velocityx = 10
        if Ballcent.x >= screen.get_width():
            scoreleft += 1
            Ballcent.x = screen.get_width()/2
            velocityx = 10
        if Ballcent.y <= 10 or Ballcent.y >= screen.get_height()-10:
            velocityy *= -1


        # Paddle1 Movement
        if keys[pg.K_a]:
            Paddle1.y -= 300 * dt
        if keys[pg.K_d]:
            Paddle1.y += 300 * dt


        # Paddle2 Movement
        if keys[pg.K_s]:
            Paddle2.y += 300*dt
        if keys[pg.K_w]:
            Paddle2.y -= 300*dt

    pg.display.flip()


    if keys[pg.K_ESCAPE]:
        pg.quit()


    dt = clock.tick(60)/1000



pg.quit()
