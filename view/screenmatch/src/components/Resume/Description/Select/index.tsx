import { useSetRecoilState } from 'recoil';
import { ISerie } from '../../../../interfaces/ISerie';
import styles from './Select.module.scss';
import { seasonSelected } from '../../../../state/atom';


export default function Select(serie: ISerie) {
  const temporadas = Array.from({ length: serie.temporada - 1 }, (_, index) => index);
  const useSetSeason = useSetRecoilState(seasonSelected);
  
  return (
    <div className={styles.container}>
      <p><b>Escolher temporada</b></p>
      <select className={styles.container__select} name="season"
        onChange={ (e) => useSetSeason(parseInt(e.currentTarget.value)) }>
        <option disabled hidden selected>Selecione a temporada</option>
        { temporadas.map((temporada, index) => ( 
          <option key={ index } value={ index + 1}>Temporada {index + 1}</option> 
        )) }
      </select>
    </div>
  );
}