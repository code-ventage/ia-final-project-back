class DuplicateUser(Exception):
    def __init__(self, message: str = 'Ya existe este usuario') -> None:
        super().__init__(self, message)
    
