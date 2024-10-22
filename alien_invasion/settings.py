import pygame
class Settings:
    """A class to store all the settings for Alien Invasion."""
    def __init__(self):
        """Initialize the game's settings."""
        # Screen settings
        self.screen_width = 1366
        self.screen_height = 700
        self.bg_color = (51, 153, 255)
        pygame.mouse.set_visible(False)

        # Ship settings
        self.ship_speed = 1.5

        # Bullet settings
        self.bullet_speed = 3
        self.bullet_width = 3
        self.bullet_height = 15
        self.bullet_color = (60, 60, 60)
        
