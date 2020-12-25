export interface IPoste {
  id?: number;
  nom?: string;
  departement?: string;
  salaire?: number;
  employeId?: number;
}

export class Poste implements IPoste {
  constructor(public id?: number, public nom?: string, public departement?: string, public salaire?: number, public employeId?: number) {}
}
