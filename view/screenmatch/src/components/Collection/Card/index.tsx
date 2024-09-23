import { useNavigate } from 'react-router-dom';
import { ISerie } from '../../../interfaces/ISerie';
import styles from './Card.module.scss';


export default function Card(serie: ISerie) {
  const navigate = useNavigate();

  function viewMore(serie: ISerie) {
    navigate('/serie/' + serie.id);
  }

  return (
    <div className={ styles.container } 
      onClick={ () => viewMore(serie) }>
      <img className={ styles.container__img } src={ serie.baner } alt={ serie.titulo } />
      <p>{ serie.titulo }</p>
    </div>
  );
}