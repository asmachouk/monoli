import { IPoste } from 'app/shared/model/poste.model';
import { IFormation } from 'app/shared/model/formation.model';

export interface IEmploye {
  id?: number;
  nom?: string;
  prenom?: string;
  adresse?: string;
  postes?: IPoste[];
  formations?: IFormation[];
}

export class Employe implements IEmploye {
  constructor(
    public id?: number,
    public nom?: string,
    public prenom?: string,
    public adresse?: string,
    public postes?: IPoste[],
    public formations?: IFormation[]
  ) {}
}
