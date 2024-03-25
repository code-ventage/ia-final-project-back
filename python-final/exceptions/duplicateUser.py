class DuplicateUser(Exception):
    """
    Exepcion lanzada al encontrar usuarios duplicados.

    Atributos:
        message (str): Explicacion del error
    """
    def __init__(self, message: str = 'Ya existe este usuario') -> None:
        super().__init__(self, message)