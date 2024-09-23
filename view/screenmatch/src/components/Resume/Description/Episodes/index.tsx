import { useRecoilValue } from 'recoil';
import styles from './Episodes.module.scss';
import { episodesAtSeason } from '../../../../state/atom';


export default function Episodes() {
  const episodesSeason = useRecoilValue(episodesAtSeason);
  if (episodesSeason.length == 0) {
    return;
  }

  return (
    <>
      <p><b>Episódios:</b></p>
      <h4>Temporada: { episodesSeason[0].numeroTemporada }</h4>
      <ul className={ styles.list }>
        { episodesSeason.map( (ep, index) => <li key={ ep.id }>{ index + 1 } - { ep.titulo }</li> ) }
      </ul>
    </>
  );
}